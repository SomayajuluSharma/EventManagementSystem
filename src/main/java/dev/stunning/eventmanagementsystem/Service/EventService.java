package dev.stunning.eventmanagementsystem.Service;

import dev.stunning.eventmanagementsystem.Models.EventModel;
import dev.stunning.eventmanagementsystem.Repository.EventRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<EventModel> saveAll(List<EventModel> events){
        return eventRepository.saveAll(events);
    }
}
