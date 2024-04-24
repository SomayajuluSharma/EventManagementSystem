package dev.stunning.eventmanagementsystem.Response;

import dev.stunning.eventmanagementsystem.DTO.EventDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EventPageResponse {
    private List<EventDto> events;
    private int page;
    private int pageSize;
    private long totalEvents;
    private int totalPages;
}
