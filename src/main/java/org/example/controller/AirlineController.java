package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.RouteDTO;
import org.example.dto.RouteDTOBase;
import org.example.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@Validated
@Tag(name = "Airline Controller", description = "airline operations")
@RequestMapping("/route")
@Slf4j
public class AirlineController {

    private final AirlineService airlineService;

    @Autowired
    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @GetMapping("/{routeId}")
    @Operation(description = "get an airline route by id")
    public ResponseEntity<RouteDTO> getOneRoute(
            @PathVariable final UUID routeId) {
        log.info("/route (GET) hit with routeId {}", routeId);
        return airlineService.getRouteById(routeId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("/airport/{fromAirport}")
    @Operation(description = "get all routes by airport")
    public List<RouteDTO> getRoutesByAirport(
            @PathVariable final String fromAirport) {
        log.info("/route endpoint hit with airport {}", fromAirport);
        return airlineService.getRoutesByAirport(fromAirport);
    }

    @PostMapping
    @Operation(description = "create a new airline route")
    public ResponseEntity<RouteDTO> createRoute(
            @RequestBody
            @Valid final RouteDTOBase routeDTO) {
        log.info("/route (POST) hit with route {}", routeDTO);
        return ResponseEntity.ok(airlineService.createRoute(routeDTO));
    }

    @PutMapping("/{routeId}")
    @Operation(description = "update an existing airline route")
    public ResponseEntity<RouteDTO> updateRoute(
            @PathVariable final UUID routeId,
            @RequestBody
            @Valid final RouteDTOBase routeDTO) {
        log.info("/route (PUT) hit with route {}", routeDTO);
        return airlineService.updateRoute(routeId, routeDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}