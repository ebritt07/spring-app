package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MainApplication {

    public static void main(String[] args) {
        log.info("Starting application. Once started," +
                "api docs at http://localhost:8445/spring-app/swagger-ui/index.html");
        SpringApplication.run(MainApplication.class, args);
    }
}