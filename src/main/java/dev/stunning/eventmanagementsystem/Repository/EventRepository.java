package dev.stunning.eventmanagementsystem.Repository;

import dev.stunning.eventmanagementsystem.DTO.EventDto;
import dev.stunning.eventmanagementsystem.Models.EventModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<EventModel, Long> {

   //List<EventModel> saveAll(List<EventModel> events);

    Page<EventModel> findAllByDateBetweenOrderByDateAsc(LocalDate startDate, LocalDate endDate, Pageable pageable);

}
