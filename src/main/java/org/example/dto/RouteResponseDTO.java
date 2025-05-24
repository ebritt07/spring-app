package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouteResponseDTO extends RouteDTO {
    Long id;
}
