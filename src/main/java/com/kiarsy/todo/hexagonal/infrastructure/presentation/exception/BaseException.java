package com.kiarsy.todo.hexagonal.infrastructure.presentation.exception;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {
    private boolean tokenExpired= false;
    private HttpStatus httpCode = HttpStatus.INTERNAL_SERVER_ERROR;


    public BaseException(String message, boolean tokenExpired, HttpStatus httpCode) {
        super(message);
        this.tokenExpired = tokenExpired;
        this.httpCode = httpCode;
    }

    public BaseException(String message, HttpStatus httpCode) {
        super(message);
        this.httpCode = httpCode;
    }

    public BaseException(String message, boolean tokenExpired) {
        super(message);
        this.tokenExpired = tokenExpired;
    }


    public boolean isTokenExpired() {
        return tokenExpired;
    }

    public HttpStatus getHttpCode() {
        return httpCode;
    }
}
