package org.example;

import org.example.dto.RouteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AirlineServiceImpl implements AirlineService {
    private static final Logger logger = LoggerFactory.getLogger(AirlineServiceImpl.class);

    public RouteDTO getRouteById(String routeId) {

        logger.info("DEV calling db to get route by routeId {}", routeId);
        return RouteDTO.builder()
                .aircraft("A320")
                .destination("SFO")
                .origin("YYC")
                .weeklySchedule("MWF")
                .build();
    }

    public List<RouteDTO> getRoutesById(List<String> routeIdList) {
        logger.info("DEV calling db to get route by routeIdList {}", routeIdList);
        final RouteDTO routeDTO = RouteDTO.builder()
                .aircraft("A320")
                .destination("SFO")
                .origin("YYC")
                .weeklySchedule("MWF")
                .build();
        return List.of(routeDTO, routeDTO, routeDTO);
    }

    public RouteDTO createRoute(RouteDTO routeDTO) {
        throw new UnsupportedOperationException("Unimplemented method 'createRoute'");
    }

    public List<RouteDTO> getRoutesByAirport(String fromAirport) {
        logger.info("Local calling db to get route by airport {}", fromAirport);
        final RouteDTO routeDTO = RouteDTO.builder()
                .aircraft("A320")
                .destination("SFO")
                .origin("YYC")
                .weeklySchedule("MWF")
                .build();

        return List.of(routeDTO, routeDTO, routeDTO);
    }
}
