package org.example;

import org.example.dto.RouteDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AirlineService {

    RouteDTO getRouteById(String routeId);

    List<RouteDTO> getRoutesById(List<String> routeIdList);

    List<RouteDTO> getRoutesByAirport(String fromAirport);

    RouteDTO createRoute(RouteDTO routeDTO);
}
