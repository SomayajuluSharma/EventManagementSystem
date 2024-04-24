package dev.stunning.eventmanagementsystem.Controller;

import dev.stunning.eventmanagementsystem.Models.EventModel;
import dev.stunning.eventmanagementsystem.Service.CsvService;
import dev.stunning.eventmanagementsystem.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
public class CsvController {

    private CsvService csvService;

    private EventService eventService;

    public CsvController(CsvService csvService, EventService eventService) {
        this.csvService = csvService;
        this.eventService = eventService;
    }

    @PostMapping("/events/upload")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
        List<EventModel> events = csvService.readCsvData(file);
        eventService.saveAll(events);
        return ResponseEntity.ok("CSV data uploaded successfully!");
    }
}
