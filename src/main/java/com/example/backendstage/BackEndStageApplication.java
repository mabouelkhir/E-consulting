package com.example.backendstage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")// Add this line to enable CORS for all endpoints

public class BackEndStageApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackEndStageApplication.class, args);
    }

}
