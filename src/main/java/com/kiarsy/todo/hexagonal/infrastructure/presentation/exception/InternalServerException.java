package com.kiarsy.todo.hexagonal.infrastructure.presentation.exception;

import org.springframework.http.HttpStatus;

public class InternalServerException extends BaseException {
    public InternalServerException(Exception e) {
        super("Unhandled error happened,Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
