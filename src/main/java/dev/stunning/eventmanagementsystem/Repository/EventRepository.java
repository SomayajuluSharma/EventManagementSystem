package dev.stunning.eventmanagementsystem.Repository;

import dev.stunning.eventmanagementsystem.Models.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<EventModel, Long> {

   // List<EventModel> saveAll(List<EventModel> events);
}
