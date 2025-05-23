package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import jakarta.websocket.server.PathParam;
import org.example.AirlineService;
import org.example.Constants;
import org.example.dto.RouteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Validated
@Tag(name = "Airline Controller", description = "airline operations")
public class AirlineController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    private static final String AIRPORT_REGEX = Constants.AIRPORT_REGEX;
    private final AirlineService airlineService;

    @Autowired
    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @GetMapping("/route/{routeId}")
    @Operation(description = "get an airline route by id")
    public RouteDTO getOneRoute(
            @Pattern(regexp = "[A-Z][0-9]") @PathVariable("routeId") final String routeId,
            @PathParam(value = "aircraft") final String aircraft) {
        log.info("/route endpoint hit with aircraft {}", aircraft);
        return airlineService.getRouteById(routeId);

    }

    @GetMapping("/routes/{fromAirport}")
    @Operation(description = "get all routes by airport")
    public List<RouteDTO> getRoutesByAirport(
            @Pattern(regexp = "^[A-Z]{3}$") @PathVariable("from airport") final String fromAirport) {
        log.info("/route endpoint hit with airport {}", fromAirport);
        return airlineService.getRoutesByAirport(fromAirport);

    }

    @PostMapping("/route/new")
    @Operation(description = "create a new airline route")
    public RouteDTO createRoute(
            @RequestParam final RouteDTO routeDTO) {

        log.info("/route/new endpoint hit with route {}", routeDTO);
        return airlineService.createRoute(routeDTO);

    }

}