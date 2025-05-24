package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouteDTO {
    String aircraft;
    String origin;
    String destination;
    ScheduleDTO schedule;
}
