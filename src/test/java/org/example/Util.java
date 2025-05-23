package org.example;

import org.example.dto.RouteDTO;

public class Util {
    public static RouteDTO getRouteDTO() {
        return RouteDTO.builder()
                .aircraft("A320")
                .destination("SFO")
                .origin("PHX")
                .weeklySchedule("MF")
                .build();
    }
}
