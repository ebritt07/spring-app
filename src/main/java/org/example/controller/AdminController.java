package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Admin Controller", description = "admin operations")
@Slf4j
public class AdminController {

    @GetMapping("/ping")
    @Operation(description = "ping!")
    public String ping() {

        log.info("ping endpoint hit...");
        return "pong!";
    }
}