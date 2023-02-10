package com.kiarsy.todo.hexagonal.core.application.exception;

import com.kiarsy.todo.hexagonal.infrastructure.presentation.exception.BaseException;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException(String message ) {
        super(message, HttpStatus.NOT_FOUND);
    }
    public ResourceNotFoundException() {
        this("Resource Not Found");
    }
}
