package com.example.backendstage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@CrossOrigin(origins = "*")// Add this line to enable CORS for all endpoints

public class BackEndStageApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackEndStageApplication.class, args);
    }

    // Configure Spring Boot to serve React build files
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/**")
                        .addResourceLocations("file:/C:/Users/moham/Documents/ABOUELKHIR_2ITE2/Stage PMI Consulting/sakai-react-7.0.2/")
                        .addResourceLocations("classpath:/META-INF/resources/webjars/");
            }
        };
    }

}
