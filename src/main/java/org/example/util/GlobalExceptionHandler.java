package org.example.util;

import org.example.dto.ErrorDTO;
import org.example.dto.ValidationErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleServiceException(ServiceException ex) {
        return ResponseEntity.status(ex.getStatus().value())
                .body(ErrorDTO.builder()
                        .error(ex.getStatus().getReasonPhrase())
                        .message(ex.getMessage())
                        .build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleValidationException(MethodArgumentNotValidException ex) {
        List<ValidationErrorDTO> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> ValidationErrorDTO.builder()
                        .field(error.getField())
                        .error(error.getDefaultMessage())
                        .build())
                .toList();

        return ResponseEntity.status(BAD_REQUEST)
                .body(ErrorDTO.builder()
                        .error(BAD_REQUEST.getReasonPhrase())
                        .validationErrors(errors)
                        .build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleBaseException(Exception ex) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(ErrorDTO.builder()
                        .error(INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .message(ex.getMessage())
                        .build()
                );
    }
}
