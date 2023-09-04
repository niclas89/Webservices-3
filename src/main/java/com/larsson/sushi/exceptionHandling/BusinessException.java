package com.larsson.sushi.exceptionHandling;

public class BusinessException extends RuntimeException{

    private int code;


    public BusinessException(String message, int code){
        super(message);
        this.code = code;
    }

    public BusinessException() {
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
