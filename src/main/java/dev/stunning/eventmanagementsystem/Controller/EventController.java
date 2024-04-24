package dev.stunning.eventmanagementsystem.Controller;


import dev.stunning.eventmanagementsystem.Service.EventService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
}
