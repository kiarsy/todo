package com.kiarsy.todo.hexagonal.infrastructure.presentation.configuration;

import com.kiarsy.todo.hexagonal.infrastructure.presentation.exception.BaseException;
import com.kiarsy.todo.hexagonal.infrastructure.presentation.exception.InternalServerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @Value("${app.debug}")
    private boolean debugMode = false;

    public ResponseEntity<Map<String, Object>> handleValidations(
            MethodArgumentNotValidException e
    ) {

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> error = new HashMap<>();
        error.put("message", "Provided data is wrong");

        var validationErrors = e.getFieldErrors().stream().map(it -> it.getDefaultMessage());
        error.put("validation", validationErrors);
        response.put("status", false);
        response.put("code", e.getStatusCode().value());
        response.put("error", error);

        return new ResponseEntity<>(response, e.getStatusCode());
    }

    public ResponseEntity<Map<String, Object>> handleCustomExceptions(
            BaseException e
    ) {
        var response = convertExceptionToErrorResponse(e).getFirst();
        return new ResponseEntity<>(response, e.getHttpCode());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllExceptions(
            Exception e
    ) {

        if (e instanceof BaseException)
            return handleCustomExceptions((BaseException) e);
        else if (e instanceof MethodArgumentNotValidException) {
            return handleValidations((MethodArgumentNotValidException) e);
        }
        e.printStackTrace();
        return handleCustomExceptions(new InternalServerException(e));
    }


    public Pair<Map<String, Object>, Integer> convertExceptionToErrorResponse(BaseException e) {
        // converting the stack trace to String
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> error = new HashMap<>();
        error.put("message", e.getMessage());

        response.put("status", false);
        response.put("code", e.getHttpCode().value());
        response.put("error", error);

        if (debugMode && e.getHttpCode() == HttpStatus.INTERNAL_SERVER_ERROR)
            response.put("stackTrace", stackTrace);
        return Pair.of(response, e.getHttpCode().value());
    }

}
