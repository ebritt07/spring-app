package org.example.service;

import org.example.db.RouteRepository;
import org.example.dto.RouteDTO;
import org.example.entity.Route;
import org.example.mapper.RouteMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AirlineService {

    RouteRepository routeRepository;
    RouteMapper routeMapper;

    public AirlineService(RouteRepository routeRepository, RouteMapper routeMapper) {
        this.routeRepository = routeRepository;
        this.routeMapper = routeMapper;
    }

    public RouteDTO getRouteById(UUID routeId) throws NotFoundException {
        Route route = routeRepository.findById(routeId).orElseThrow(NotFoundException::new);
        return routeMapper.toRouteDTO(route);
    }

    public List<RouteDTO> getRoutesByAirport(String fromAirport) {
        return routeRepository.findByOrigin(fromAirport).stream()
                .map(routeMapper::toRouteDTO)
                .toList();
    }

    public RouteDTO createRoute(RouteDTO routeDTO) {
        routeDTO.setId(null);
        Route route = routeMapper.toRoute(routeDTO);
        Route result = routeRepository.save(route);
        return routeMapper.toRouteDTO(result);
    }
}
