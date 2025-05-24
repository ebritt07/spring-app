//package org.example;
//
//import org.example.controller.AirlineController;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//public class ControllerTest {
//
//    private MockMvc mockMvc;
//
//    @InjectMocks
//    private AirlineController airlineController;
//aa
//    @Mock
//    private AirlineService airlineService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(airlineController).build();
//    }
//
//    @Test
//    public void testGetRoute() throws Exception {
//        when(airlineService.getRouteById("AB123")).thenReturn(Util.getRouteDTO());
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/route/{routeId}", "AB123")
//                        .param("aircraft", "a340"))
//                .andExpect(status().isOk());
//
//        Mockito.verify(airlineService, Mockito.times(1)).getRouteById("AB123");
//        Mockito.verifyNoMoreInteractions(airlineService);
//    }
//}
