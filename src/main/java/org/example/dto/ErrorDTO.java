package org.example.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ErrorDTO {
    String error;
    String message;
    List<ValidationErrorDTO> validationErrors;
}
