package com.programming.techie.springredditclone.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
