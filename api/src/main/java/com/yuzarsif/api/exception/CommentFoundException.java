package com.yuzarsif.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommentFoundException extends RuntimeException {

    public CommentFoundException(String message) {
        super(message);
    }
}
