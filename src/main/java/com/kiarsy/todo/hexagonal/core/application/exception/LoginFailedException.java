package com.kiarsy.todo.hexagonal.core.application.exception;

import com.kiarsy.todo.hexagonal.infrastructure.presentation.exception.BaseException;
import org.springframework.http.HttpStatus;

public class LoginFailedException extends BaseException {
    public LoginFailedException() {
        super("Username/Password incorrect", HttpStatus.BAD_REQUEST);
    }
}
