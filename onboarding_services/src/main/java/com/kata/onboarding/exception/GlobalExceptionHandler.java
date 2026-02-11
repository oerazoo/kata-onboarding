package com.kata.onboarding.exception;

import org.mapstruct.ap.shaded.freemarker.core.UnexpectedTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<Error> alreadyExistsHandler(ResourceAlreadyExistsException exception){
        Error error = new Error("already-exists", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> invalidValueHandler(HttpMessageNotReadableException exception){

        String message = "Invalid value";

        if(exception.getMessage() != null && exception.getMessage().contains("enums.DocumentType")){
            message = "Document type not allowed. Please use: [CC, CE, PAS]";
        }

        Error error = new Error("invalid-value", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Error> notFoundHandler(NotFoundException exception){
        Error error = new Error("not-found", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(CustomerHasAccountException.class)
    public ResponseEntity<Error> hasAccountHandler(CustomerHasAccountException exception){
        Error error = new Error("customer-has-account", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> ArgumentNotValidHandler(MethodArgumentNotValidException exception){

        String details = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(" | "));

        Error error = new Error("argument-not-valid", details);

        return ResponseEntity.badRequest().body(error);
    }



}
