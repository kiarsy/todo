package com.kiarsy.todo.hexagonal.core.application.exception;

import com.kiarsy.todo.hexagonal.infrastructure.presentation.exception.BaseException;
import org.springframework.http.HttpStatus;

public class UsernameNotUniqueException extends BaseException {
    public UsernameNotUniqueException() {
        super("Username you is already taken, Please provide another username", HttpStatus.CONFLICT);
    }
}
