package com.kiarsy.todo.hexagonal.infrastructure.presentation.pojo;

public class Response<T> {
    private long code = 200;
    private boolean status = true;
    private T result;

    public Response(long code, boolean status, T result) {
        this.code = code;
        this.status = status;
        this.result = result;
    }

    public Response(boolean status, T result) {
        this.status = status;
        this.result = result;
    }

    public Response() {

    }

    public Response(T result) {
        this.result = result;
    }

    public long getCode() {
        return code;
    }

    public boolean isStatus() {
        return status;
    }

    public T getResult() {
        return result;
    }


}
