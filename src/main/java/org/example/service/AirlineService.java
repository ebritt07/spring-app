package org.example.service;

import org.example.db.RouteRepository;
import org.example.dto.RouteDTO;
import org.example.dto.RouteResponseDTO;
import org.example.entity.Route;
import org.example.mapper.RouteMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineService {

    RouteRepository routeRepository;
    RouteMapper routeMapper;

    public AirlineService(RouteRepository routeRepository, RouteMapper routeMapper) {
        this.routeRepository = routeRepository;
        this.routeMapper = routeMapper;
    }

    public RouteResponseDTO getRouteById(Long routeId) throws NotFoundException {
        Route route = routeRepository.findById(routeId).orElseThrow(NotFoundException::new);
        return routeMapper.toResponse(route);
    }

    public List<RouteResponseDTO> getRoutesByAirport(String fromAirport) {
        return routeRepository.findByOrigin(fromAirport).stream()
                .map(routeMapper::toResponse)
                .toList();
    }

    public RouteResponseDTO createRoute(RouteDTO routeDTO) {
        Route route = routeMapper.toEntity(routeDTO);
        routeRepository.save(route);
        return routeMapper.toResponse(route);
    }
}
