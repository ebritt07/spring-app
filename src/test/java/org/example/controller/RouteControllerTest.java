package org.example.controller;

import org.example.service.AirlineService;
import org.example.util.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;
import java.util.UUID;

import static org.example.testutil.TestUtil.getOneRouteDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RouteControllerTest {
    AirlineService airlineService = mock(AirlineService.class);
    private final RouteController routeController = new RouteController(airlineService);
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(routeController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void testGetRouteOK() throws Exception {
        when(airlineService.getRouteById(any(UUID.class))).thenReturn(Optional.of(getOneRouteDTO()));
        UUID uuid = UUID.randomUUID();
        mockMvc.perform(MockMvcRequestBuilders.get("/route/{routeId}", uuid))
                .andExpect(status().isOk());
        Mockito.verify(airlineService, Mockito.times(1)).getRouteById(uuid);
        Mockito.verifyNoMoreInteractions(airlineService);
    }

    @Test
    public void testGetRouteException() throws Exception {
        when(airlineService.getRouteById(any(UUID.class))).thenThrow(new RuntimeException("some error message"));
        UUID uuid = UUID.randomUUID();
        mockMvc.perform(MockMvcRequestBuilders.get("/route/{routeId}", uuid))
                .andExpect(status().isInternalServerError())
                .andExpect(content().json("{'error':'Internal Server Error', 'message':'some error message'}"));
        Mockito.verify(airlineService, Mockito.times(1)).getRouteById(uuid);
        Mockito.verifyNoMoreInteractions(airlineService);
    }

    @Test
    public void testServiceException() throws Exception {
        when(airlineService.getRouteById(any(UUID.class))).thenThrow(new ServiceException(HttpStatus.TOO_MANY_REQUESTS, "intruder alert..."));
        UUID uuid = UUID.randomUUID();
        mockMvc.perform(MockMvcRequestBuilders.get("/route/{routeId}", uuid))
                .andExpect(status().isTooManyRequests())
                .andExpect(content().json("{'error':'Too Many Requests', 'message':'intruder alert...'}"));
        Mockito.verify(airlineService, Mockito.times(1)).getRouteById(uuid);
        Mockito.verifyNoMoreInteractions(airlineService);
    }

    @Test
    public void testBadUrl() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/route/foo/bar"))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{'error':'Not Found','message':'No endpoint GET /spring-app/pong.'}"));
    }
}
