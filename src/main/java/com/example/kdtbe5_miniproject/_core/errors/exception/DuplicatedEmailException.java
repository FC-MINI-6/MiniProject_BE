package com.example.kdtbe5_miniproject._core.errors.exception;

public class DuplicatedEmailException extends RuntimeException{

    public DuplicatedEmailException(String message) {
        super(message);
    }
}
