package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.ErrorDTO;
import org.example.dto.ValidationErrorDTO;
import org.example.util.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@Slf4j
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
    public ResponseEntity<ErrorDTO> handleBadRoute(NoHandlerFoundException ex) {
        return ResponseEntity.status(NOT_FOUND)
                .body(ErrorDTO.builder()
                        .error(NOT_FOUND.getReasonPhrase())
                        .message(ex.getMessage())
                        .build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleBaseException(Exception ex) {
        log.error("(500) thrown: {}", ex.getMessage());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(ErrorDTO.builder()
                        .error(INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .message(ex.getMessage())
                        .build()
                );
    }
}
