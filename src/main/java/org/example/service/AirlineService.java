package org.example.service;

import org.example.db.RouteRepository;
import org.example.dto.RouteDTO;
import org.example.dto.RouteDTOBase;
import org.example.entity.Route;
import org.example.mapper.RouteMapper;
import org.example.util.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.example.util.Constants.SAME_ORIGIN_DESTINATION;

@Service
public class AirlineService {

    RouteRepository routeRepository;
    RouteMapper routeMapper;

    public AirlineService(RouteRepository routeRepository, RouteMapper routeMapper) {
        this.routeRepository = routeRepository;
        this.routeMapper = routeMapper;
    }

    public Optional<RouteDTO> getRouteById(UUID routeId) {
        return routeRepository.findById(routeId).map(routeMapper::toRouteDTO);
    }

    public List<RouteDTO> getRoutesByAirport(String fromAirport) {
        return routeRepository.findByOrigin(fromAirport).stream()
                .map(routeMapper::toRouteDTO)
                .toList();
    }

    public RouteDTO createRoute(RouteDTOBase routeDTOBase) {

        if (Objects.equals(routeDTOBase.getDestination(), routeDTOBase.getOrigin())) {
            throw new ServiceException(SAME_ORIGIN_DESTINATION);
        }

        Route route = routeMapper.toRoute(routeDTOBase);
        Route result = routeRepository.save(route);
        return routeMapper.toRouteDTO(result);
    }

    public Optional<RouteDTO> updateRoute(UUID routeId, RouteDTOBase routeDTOBase) {
        return routeRepository.findById(routeId)
                .map(existing -> {
                    Route updated = routeMapper.toRoute(routeDTOBase);
                    updated.setId(routeId);
                    return routeRepository.save(updated);
                })
                .map(routeMapper::toRouteDTO);
    }
}
