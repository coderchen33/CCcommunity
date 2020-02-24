package com.company.community.community.exception;

public class CustomizeException extends RuntimeException{
    private String message;

    public CustomizeException(ICustomizeExceptionCode errorCode) {
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
