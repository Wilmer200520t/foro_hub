package com.forohub.general.infra.errors;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
