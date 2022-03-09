package com.example.zuche.exception;

public class CustomizedErrorException extends RuntimeException {
    private static final long serialVersionUID = -4241069198209638147L;

    public CustomizedErrorException() {
    }

    public CustomizedErrorException(String message) {
        super(message);
    }

    public CustomizedErrorException(String message, ReflectiveOperationException e) {
        super(message, e.getCause());
    }

}
