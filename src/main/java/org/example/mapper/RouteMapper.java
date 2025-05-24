package org.example.mapper;

import org.example.dto.RouteDTO;
import org.example.dto.RouteResponseDTO;
import org.example.entity.Route;
import org.mapstruct.Mapper;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RouteMapper {
    Route toEntity(RouteDTO routeDTO);

    RouteResponseDTO toResponse(Route route);

    default Set<DayOfWeek> mapToDayOfWeek(Set<String> input) {
        return input.stream()
                .map(String::toUpperCase)
                .map(DayOfWeek::valueOf)
                .collect(Collectors.toSet());
    }

    default Set<String> mapFromDayOfWeek(Set<DayOfWeek> input) {
        return input.stream()
                .map(DayOfWeek::name)
                .collect(Collectors.toSet());
    }

    default Set<Month> mapToMonth(Set<String> input) {
        return input.stream()
                .map(String::toUpperCase)
                .map(Month::valueOf)
                .collect(Collectors.toSet());
    }

    default Set<String> mapFromMonth(Set<Month> input) {
        return input.stream()
                .map(Month::name)
                .collect(Collectors.toSet());
    }
}
