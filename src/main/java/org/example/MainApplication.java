package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

    private static final Logger log = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {
        log.info("Starting application. Once started," +
                "api docs at http://localhost:8445/spring-app/swagger-ui/index.html");
        SpringApplication.run(MainApplication.class, args);
    }
}