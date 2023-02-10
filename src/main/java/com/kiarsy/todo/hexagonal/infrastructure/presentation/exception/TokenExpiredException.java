package com.kiarsy.todo.hexagonal.infrastructure.presentation.exception;

import org.springframework.http.HttpStatus;

public class TokenExpiredException extends BaseException {
    public TokenExpiredException() {
        super("Your token is expired", true,HttpStatus.UNAUTHORIZED);
    }
}
