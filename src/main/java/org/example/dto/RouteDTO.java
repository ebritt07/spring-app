package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouteDTO {

    private String origin;
    private String destination;
    private String aircraft;

    // eg. "MWF" // "MTuWThFSaSu"
    private String weeklySchedule;

}
