package com.cgm.cgmcodingchallenge.exceptions;

public class PatientNotFoundException extends RuntimeException{
    public PatientNotFoundException(String message, Throwable throwable){
        super(message, throwable);
    }

    public PatientNotFoundException(String message){
        super(message);
    }
}
