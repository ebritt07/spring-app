package org.example.mapper;

import org.example.dto.RouteDTO;
import org.example.dto.RouteDTOBase;
import org.example.entity.Route;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ScheduleMapper.class)
public interface RouteMapper {

    @Mapping(target = "id", ignore = true)
    Route toRoute(RouteDTOBase routeDTOBase);

    RouteDTO toRouteDTO(Route route);
}
