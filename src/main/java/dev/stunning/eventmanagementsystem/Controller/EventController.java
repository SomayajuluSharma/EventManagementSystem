package dev.stunning.eventmanagementsystem.Controller;


import dev.stunning.eventmanagementsystem.DTO.EventDto;
import dev.stunning.eventmanagementsystem.Response.EventPageResponse;
import dev.stunning.eventmanagementsystem.Service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.List;

@RestController
public class EventController {
    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events/find")
    public ResponseEntity<EventPageResponse> getEventsNearUser(@RequestParam double latitude, @RequestParam double longitude, @RequestParam String date){
        Pageable pageable = PageRequest.of(0, 10);
      //  Pageable pageable = PageRequest.of(page.getPageNumber(), pageable.getPageSize(), Sort.by("date");
        EventPageResponse response  = eventService.getEventsNearUser(latitude, longitude, date, pageable);
        return ResponseEntity.ok(response);
    }
}
