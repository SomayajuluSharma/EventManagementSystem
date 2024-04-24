package dev.stunning.eventmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class EventManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventManagementSystemApplication.class, args);
    }

}
