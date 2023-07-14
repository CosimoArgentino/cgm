package com.cgm.cgmcodingchallenge.exceptions;

public class InvalidSecurityNumberException extends RuntimeException{
    public InvalidSecurityNumberException(String message, Throwable throwable){
        super(message, throwable);
    }

    public InvalidSecurityNumberException(String message){
        super(message);
    }
}
