package com.forohub.general.infra.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Errors {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> exception(ValidationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
