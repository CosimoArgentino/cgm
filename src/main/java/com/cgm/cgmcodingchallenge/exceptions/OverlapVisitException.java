package com.cgm.cgmcodingchallenge.exceptions;

public class OverlapVisitException extends RuntimeException{
    public OverlapVisitException(String message, Throwable throwable){
        super(message, throwable);
    }

    public OverlapVisitException(String message){
        super(message);
    }
}
