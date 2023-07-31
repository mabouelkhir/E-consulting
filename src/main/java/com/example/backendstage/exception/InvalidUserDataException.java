package com.example.backendstage.exception;

public class InvalidUserDataException extends RuntimeException{
    public InvalidUserDataException(String message) {
        super(message);
    }
}
