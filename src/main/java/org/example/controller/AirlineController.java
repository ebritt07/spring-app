package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.dto.RouteDTO;
import org.example.service.AirlineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@Validated
@Tag(name = "Airline Controller", description = "airline operations")
@RequestMapping("/route")
public class AirlineController {

    private static final Logger log = LoggerFactory.getLogger(AirlineController.class);
    private final AirlineService airlineService;

    @Autowired
    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @GetMapping("/{routeId}")
    @Operation(description = "get an airline route by id")
    public ResponseEntity<RouteDTO> getOneRoute(
            @PathVariable final UUID routeId) throws NotFoundException {
        log.info("/route endpoint hit with routeId {}", routeId);
        return ResponseEntity.ok(airlineService.getRouteById(routeId));
    }

    @GetMapping("/airport/{fromAirport}")
    @Operation(description = "get all routes by airport")
    public List<RouteDTO> getRoutesByAirport(
            @PathVariable final String fromAirport) {
        log.info("/route endpoint hit with airport {}", fromAirport);
        return airlineService.getRoutesByAirport(fromAirport);
    }

    @PostMapping("/new")
    @Operation(description = "create a new airline route")
    public RouteDTO createRoute(
            @RequestBody final RouteDTO routeDTO) {
        log.info("/route/new endpoint hit with route {}", routeDTO);
        return airlineService.createRoute(routeDTO);
    }

}