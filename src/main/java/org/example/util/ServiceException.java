package org.example.util;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class ServiceException extends RuntimeException {
    @Getter
    private final HttpStatus status;
    @Getter
    private final String type = this.getClass().getCanonicalName();

    public ServiceException(String message) {
        super(message);
        this.status = BAD_REQUEST;
    }

    public ServiceException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
