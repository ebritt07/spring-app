package org.example.testutil;

import org.example.dto.RouteDTO;
import org.example.dto.RouteDTOBase;
import org.example.dto.ScheduleDTO;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.JANUARY;

public class TestUtil {

    private static final Random RANDOM = new Random();
    static List<String> ORIGINS = List.of("SFO", "JFK", "LAX");
    static List<String> DESTINATIONS = List.of("PDX", "SEA", "DEN");

    private static <T> T chooseOne(Collection<T> choices) {
        int index = RANDOM.nextInt(choices.size());
        return choices.stream().skip(index).findFirst().orElseThrow();
    }

    public static RouteDTO getOneRouteDTO() {
        return RouteDTO.builder()
                .destination(chooseOne(DESTINATIONS))
                .origin(chooseOne(ORIGINS))
                .aircraft("A320")
                .schedule(ScheduleDTO.builder()
                        .weeklySchedule(Set.of(TUESDAY))
                        .months(Set.of(JANUARY))
                        .build())
                .id(UUID.randomUUID())
                .build();
    }

    public static RouteDTOBase getOneRouteDTOBase() {
        return getOneRouteDTO();
    }
}
