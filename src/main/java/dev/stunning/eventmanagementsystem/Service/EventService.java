package dev.stunning.eventmanagementsystem.Service;

import dev.stunning.eventmanagementsystem.DTO.EventDto;
import dev.stunning.eventmanagementsystem.Models.EventModel;
import dev.stunning.eventmanagementsystem.Repository.EventRepository;
import dev.stunning.eventmanagementsystem.Response.EventPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.data.domain.Page;
import java.time.LocalDate;
import java.util.*;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Value("${openweathermap.api.key}")
    private String openWeatherMapApiKey;

    private final RestTemplate restTemplate;
    private final WebClient.Builder webClientBuilder;

    public EventService(RestTemplateBuilder restTemplateBuilder, WebClient.Builder webClientBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        this.webClientBuilder = webClientBuilder;
    }

    public List<EventModel> saveAll(List<EventModel> events){
        return eventRepository.saveAll(events);
    }

    public EventPageResponse getEventsNearUser(double latitude, double longitude, String date, Pageable pageable){
        LocalDate startDate = LocalDate.parse(date);
        LocalDate endDate = startDate.plusDays(14);

        Page<EventModel> events = eventRepository.findAllByDateBetweenOrderByDateAsc(startDate, endDate, pageable);
        List<EventDto> eventDtos = new ArrayList<>();

        for(EventModel event : events.getContent()){
            double eventLatitude = event.getLatitude();
            double eventLongitude = event.getLongitude();

            double distance = calculateDistance(latitude, longitude, eventLatitude, eventLongitude);

             String weather = getWeather(eventLatitude + "," + eventLongitude);

            EventDto eventDto = new EventDto();
            eventDto.setEventName(event.getEventName());
            eventDto.setCityName(event.getCityName());
            eventDto.setDate(String.valueOf(event.getDate()));
            eventDto.setDistanceKm(distance);
            eventDto.setWeather(weather);

            eventDtos.add(eventDto);
        }
       // Collections.sort(eventDtos, Comparator.comparing(EventDto::getDate));

        EventPageResponse response = new EventPageResponse();
        response.setEvents(eventDtos);
        response.setPage(events.getNumber() + 1); // 0-based index
        response.setPageSize(events.getSize());
        response.setTotalEvents(events.getTotalElements());
        response.setTotalPages(events.getTotalPages());

        return response;

    }

    private double calculateDistance(double sourceLatitude, double sourceLongitude, double eventLatitude, double eventLongitude){
        double earthRadius = 6371;

        double dLat = Math.toRadians(eventLatitude - sourceLatitude);
        double dLon = Math.toRadians(eventLongitude - sourceLongitude);

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(Math.toRadians(sourceLatitude)) * Math.cos(Math.toRadians(eventLatitude)) * Math.sin(dLon/2) * Math.sin(dLon/2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return earthRadius * c;
    }

    private String getWeather(String latLong) {
        String[] latLongArr = latLong.split(",");
        String latitude = latLongArr[0];
        String longitude = latLongArr[1];

        String url = "https://api.openweathermap.org/data/2.5/weather?lat={latitude}&lon={longitude}&appid={openWeatherMapApiKey}";

        return webClientBuilder.build()
                .get()
                .uri(url, latitude, longitude, openWeatherMapApiKey)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    Map<String, Object> main = (Map<String, Object>) response.get("main");
                    List<Map<String, Object>> weatherList = (List<Map<String, Object>>) response.get("weather");

                    String description = weatherList.get(0).get("description").toString();
                    Double temp = (Double) main.get("temp");
                    int celsiusTemp = (int) (temp - 273.15);  // Convert temperature from Kelvin to Celsius

                    return description + " " + celsiusTemp + "C";
                })
                .block();
    }
}
