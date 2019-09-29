package com.programming.techie.springredditclone.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
