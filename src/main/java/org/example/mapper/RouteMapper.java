package org.example.mapper;

import org.example.dto.RouteDTO;
import org.example.entity.Route;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ScheduleMapper.class)
public interface RouteMapper {
    Route toRoute(RouteDTO routeDTO);

    RouteDTO toRouteDTO(Route route);
}
