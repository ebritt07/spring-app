package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
public class RouteDTOBase {
    @JsonProperty
    @NotBlank(message = "aircraft is required")
    @Pattern(regexp = "^[A-Z0-9]{1,10}$", message = "Aircraft must be ICAO code")
    String aircraft;

    @JsonProperty
    @NotBlank(message = "Origin is required")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Airport must be 3 letter IATA code")
    String origin;

    @JsonProperty
    @NotBlank(message = "Dest is required")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Airport must be 3 letter IATA code")
    String destination;

    @JsonProperty
    ScheduleDTO schedule;
}
