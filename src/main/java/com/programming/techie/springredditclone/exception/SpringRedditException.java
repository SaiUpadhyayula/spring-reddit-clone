package com.programming.techie.springredditclone.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ResponseStatus(INTERNAL_SERVER_ERROR)
public class SpringRedditException extends RuntimeException {
    public SpringRedditException(String message) {
        super(message);
    }
}
