package org.example;

import org.example.dto.RouteDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceTest {

    private final AirlineServiceImpl airlineService = new AirlineServiceImpl();


    @Test
    void testGetRoute() {
        RouteDTO res = airlineService.getRouteById("abc123");
        assertEquals("A320", res.getAircraft());
    }

}

