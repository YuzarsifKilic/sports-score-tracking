package com.yuzarsif.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FootballFanNotFoundException extends RuntimeException {

    public FootballFanNotFoundException(String message) {
        super(message);
    }
}
