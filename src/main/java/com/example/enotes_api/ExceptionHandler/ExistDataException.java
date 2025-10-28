package com.example.enotes_api.ExceptionHandler;

public class ExistDataException extends RuntimeException {
    public ExistDataException (String message){
        super(message);
    }
}
