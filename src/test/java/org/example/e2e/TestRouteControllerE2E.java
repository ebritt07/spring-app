package org.example.e2e;

import org.example.db.RouteRepository;
import org.example.dto.ErrorDTO;
import org.example.dto.RouteDTO;
import org.example.dto.RouteDTOBase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static org.example.testutil.TestUtil.getOneRoute;
import static org.example.util.Constants.SAME_ORIGIN_DESTINATION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class TestRouteControllerE2E {

    static UUID FAKE_UUID = UUID.randomUUID();
    final String baseUrl;
    final RestTemplate restTemplate;
    final RouteRepository routeRepository;
    RouteDTO routeDTO;

    @Autowired
    public TestRouteControllerE2E(RouteRepository routeRepository,
                                  @Value("${server.port}")
                                  Integer port,
                                  @Value("${server.servlet.context-path}")
                                  String contextPath) {
        this.restTemplate = new RestTemplate();
        this.routeRepository = routeRepository;
        this.baseUrl = "http://localhost:" + port + contextPath + "/route";
    }

    private void saveTestData(RouteDTO routeDTO) {
        this.routeDTO = routeDTO;
    }


    @BeforeAll
    public void setup() {
        routeRepository.deleteAll();
    }


    @Test
    @Order(1)
    public void addRoute() {
        RouteDTOBase newRoute = getOneRoute();
        ResponseEntity<RouteDTO> postResponse = restTemplate.postForEntity(baseUrl, newRoute, RouteDTO.class);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        RouteDTO postResult = postResponse.getBody();
        assertNotNull(postResult);
        assertEquals(newRoute.getOrigin(), postResult.getOrigin());
        saveTestData(postResult);
    }

    @Test
    @Order(2)
    public void testGetEmptyRoute() {
        String url = baseUrl + "/" + FAKE_UUID;
        try {
            restTemplate.getForEntity(url, RouteDTO.class);
            fail("Expected a 404 response");
        } catch (HttpClientErrorException e) {
            assertEquals(NOT_FOUND, e.getStatusCode());
        }
    }

    @Test
    @Order(3)
    public void testUpdateRoute() {
        String url = baseUrl + "/" + this.routeDTO.getId();
        this.routeDTO.setOrigin("YYZ");
        HttpEntity<RouteDTO> putRequest = new HttpEntity<>(routeDTO);
        ResponseEntity<RouteDTO> putResponse = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                putRequest,
                RouteDTO.class
        );
        assertEquals(HttpStatus.OK, putResponse.getStatusCode());
        RouteDTO putResult = putResponse.getBody();
        assertNotNull(putResult);
        assertEquals("YYZ", putResult.getOrigin());
        saveTestData(putResult);
    }

//    @Test
//    @Order(4)
//    public void testGetUpdatedRoute() {
//        String url = baseUrl + "/" + this.routeDTO.getId();
//        ResponseEntity<RouteDTO> getResponse = restTemplate.getForEntity(url, RouteDTO.class);
//        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
//        RouteDTO getResult = getResponse.getBody();
//        assertNotNull(getResult);
//        assertEquals(routeDTO, getResult);
//    }
//
//    @Test
//    @Order(5)
//    public void testUpdateEmptyRoute() {
//        String url = baseUrl + "/" + FAKE_UUID;
//        HttpEntity<RouteDTO> putRequest = new HttpEntity<>(routeDTO);
//        try {
//            restTemplate.exchange(
//                    url,
//                    HttpMethod.PUT,
//                    putRequest,
//                    RouteDTO.class
//            );
//            fail("Expected a 404 response");
//        } catch (HttpClientErrorException e) {
//            assertEquals(NOT_FOUND, e.getStatusCode());
//        }
//    }
//
//    @Test
//    @Order(6)
//    public void testSaveBadRoute() {
//        RouteDTOBase newRoute = getOneRoute();
//        newRoute.setDestination("SFO");
//        newRoute.setOrigin("SFO");
//        HttpEntity<RouteDTOBase> request = new HttpEntity<>(newRoute);
//        try {
//            restTemplate.exchange(
//                    baseUrl,
//                    HttpMethod.POST,
//                    request,
//                    RouteDTO.class
//            );
//            fail("Expected a 400 response");
//        } catch (HttpClientErrorException e) {
//            assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
//            ErrorDTO errorResult = e.getResponseBodyAs(ErrorDTO.class);
//            assertNotNull(errorResult);
//            assertEquals(SAME_ORIGIN_DESTINATION, errorResult.getMessage());
//        }
//    }
//
//    @Test
//    @Order(7)
//    public void testMakeBadUpdate() {
//        String url = baseUrl + "/" + this.routeDTO.getId();
//        this.routeDTO.setOrigin("abcdefg");
//        HttpEntity<RouteDTO> putRequest = new HttpEntity<>(routeDTO);
//        try {
//            restTemplate.exchange(
//                    url,
//                    HttpMethod.PUT,
//                    putRequest,
//                    RouteDTO.class
//            );
//            fail("Expected a 400 response");
//        } catch (HttpClientErrorException e) {
//            assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
//            ErrorDTO errorResult = e.getResponseBodyAs(ErrorDTO.class);
//            assertNotNull(errorResult);
//            assertEquals("origin", errorResult.getValidationErrors().getFirst().getField());
//            assertEquals("Airport must be 3 letter IATA code", errorResult.getValidationErrors().getFirst().getError());
//        }
//    }
}
