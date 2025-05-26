package org.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ErrorDTO {
    String error;
    String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<ValidationErrorDTO> validationErrors;
}
