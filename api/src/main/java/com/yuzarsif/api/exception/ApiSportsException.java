package com.yuzarsif.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApiSportsException extends RuntimeException {

    public ApiSportsException(String message) {
        super(message);
    }
}
