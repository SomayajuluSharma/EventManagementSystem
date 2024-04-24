package dev.stunning.eventmanagementsystem.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "event_model", indexes = {@Index(name = "index_date", columnList = "date")})
public class EventModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventName;
    private String cityName;
    private LocalDate date;
    private LocalTime time;
    private Double latitude;
    private Double longitude;

}
