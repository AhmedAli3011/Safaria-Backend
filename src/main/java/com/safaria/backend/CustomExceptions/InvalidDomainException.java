package com.safaria.backend.CustomExceptions;

public class InvalidDomainException extends RuntimeException {

    public InvalidDomainException(String message) {
        super(message);
    }

    public InvalidDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}