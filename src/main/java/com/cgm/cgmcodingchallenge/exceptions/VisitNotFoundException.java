package com.cgm.cgmcodingchallenge.exceptions;

public class VisitNotFoundException extends RuntimeException{
    public VisitNotFoundException(String message, Throwable throwable){
        super(message, throwable);
    }

    public VisitNotFoundException(String message){
        super(message);
    }
}
