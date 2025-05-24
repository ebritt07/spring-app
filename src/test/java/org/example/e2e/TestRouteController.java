package org.example.e2e;

import lombok.extern.slf4j.Slf4j;
import org.example.db.RouteRepository;
import org.example.dto.RouteDTO;
import org.example.dto.ScheduleDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

import static java.time.DayOfWeek.MONDAY;
import static java.time.Month.DECEMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestRouteController {
    private final RestTemplate restTemplate;
    private final RouteRepository routeRepository;
    @Value("${server.port}")
    Integer port;
    @Value("${server.servlet.context-path}")
    String contextPath;

    @Autowired
    public TestRouteController(RouteRepository routeRepository) {
        this.restTemplate = new RestTemplate();
        this.routeRepository = routeRepository;
    }

    private String getBaseUrl() {
        return "http://localhost:" + port + contextPath + "/route";
    }

    @BeforeAll
    void setup() {
        routeRepository.deleteAll();
    }

    @Test
    void testCreateAndGetRoute() {
        RouteDTO routeDTO = RouteDTO.builder()
                .origin("SFO")
                .destination("LAX")
                .aircraft("737 Max-10")
                .schedule(ScheduleDTO.builder()
                        .months(Set.of(DECEMBER))
                        .weeklySchedule(Set.of(MONDAY))
                        .build())
                .build();
        String url = getBaseUrl() + "/new";
        log.info("hitting endpoint {} (post)", url);
        ResponseEntity<RouteDTO> postResponse = restTemplate.postForEntity(url, routeDTO, RouteDTO.class);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        RouteDTO postResult = postResponse.getBody();
        assertNotNull(postResult);
        assertEquals("SFO", postResult.getOrigin());


        url = getBaseUrl() + "/" + postResult.getId();
        log.info("hitting endpoint {} (get)", url);
        ResponseEntity<RouteDTO> getResponse = restTemplate.getForEntity(url, RouteDTO.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        RouteDTO getResult = getResponse.getBody();
        assertNotNull(getResult);
        assertEquals("SFO", getResult.getOrigin());
        assertEquals(postResult, getResult);
    }
}
