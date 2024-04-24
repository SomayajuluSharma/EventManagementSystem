package dev.stunning.eventmanagementsystem.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDto {
    private String eventName;
    private String cityName;
    private String date;
    private String weather;
    private Double distanceKm;
}
