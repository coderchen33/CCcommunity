package com.company.community.community.exception;

public enum CustomrizeExceptionCode implements ICustomizeExceptionCode{

    QUESTION_NOT_FOUND("你找的问题不在了,请换一个试试?");

    private String message;

    CustomrizeExceptionCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
