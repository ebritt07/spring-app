package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class RouteDTO {
    @JsonProperty
    String aircraft;
    @JsonProperty
    String origin;
    @JsonProperty
    String destination;
    @JsonProperty
    ScheduleDTO schedule;
    @JsonProperty
    UUID id;
}
