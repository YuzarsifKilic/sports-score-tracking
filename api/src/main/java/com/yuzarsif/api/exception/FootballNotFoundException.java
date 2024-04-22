package com.yuzarsif.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FootballNotFoundException extends RuntimeException {

    public FootballNotFoundException(String message) {
        super(message);
    }
}
