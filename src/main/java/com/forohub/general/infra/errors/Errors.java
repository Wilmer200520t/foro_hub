package com.forohub.general.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Errors {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> exception(ValidationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class) //cuando se encuentra este error ejecuta la funcion
    public ResponseEntity<?> Error404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> Error400(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors().stream().map(DataError400::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    private record DataError400(String field , String message){
        public DataError400(FieldError fieldError){
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        if (ex.getMessage().contains("Cannot insert duplicate key row")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate key error: " + ex.getMostSpecificCause().getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Data integrity violation: " + ex.getMostSpecificCause().getMessage());
    }
}
