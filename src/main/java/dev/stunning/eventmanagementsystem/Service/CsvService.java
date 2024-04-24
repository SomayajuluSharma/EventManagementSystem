package dev.stunning.eventmanagementsystem.Service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import dev.stunning.eventmanagementsystem.Models.EventModel;
import jdk.jfr.Event;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {

    public List<EventModel> readCsvData(MultipartFile file) {
        List<EventModel> events = new ArrayList<>();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm:ss");
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] nextRecord;
            csvReader.readNext();
            while ((nextRecord = csvReader.readNext()) != null) {
                EventModel event = new EventModel();
                event.setEventName(nextRecord[0]);
                event.setCityName(nextRecord[1]);
                event.setDate(LocalDate.parse(nextRecord[2]));
                event.setTime(LocalTime.parse(nextRecord[3], timeFormatter));
                event.setLatitude(Double.parseDouble(nextRecord[4]));
                event.setLongitude(Double.parseDouble(nextRecord[5]));
                events.add(event);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return events;
    }
}
