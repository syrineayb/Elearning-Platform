package com.pfe.elearning.handler;

import com.pfe.elearning.exception.ObjectValidationException;
import com.pfe.elearning.exception.OperationNonPermittedException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(ObjectValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handle(ObjectValidationException exp) {
        return ExceptionResponse
                .builder()
                .errorMsg("Object not valid")
                .errorSource(exp.getViolationSource())
                .validationErrors(exp.getViolations())
                .build();
    }
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handle(EntityNotFoundException exp) {
        return ExceptionResponse
                .builder()
                .errorMsg(exp.getMessage())
                .build();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handle(UsernameNotFoundException exp) {
        return ExceptionResponse
                .builder()
                .errorMsg(exp.getMessage())
                .build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handle(BadCredentialsException exp) {
        return ExceptionResponse
                .builder()
                .errorMsg("Username and / or password is incorrect")
                .build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse handle(AccessDeniedException exp) {
        return ExceptionResponse
                .builder()
                .errorMsg("Access denied")
                .build();
    }

    @ExceptionHandler(DisabledException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handle() {
        return ExceptionResponse
                .builder()
                .errorMsg("The user is disabled. Please contact the admin")
                .build();
    }
    @ExceptionHandler(OperationNonPermittedException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ExceptionResponse handle(OperationNonPermittedException exp) {
        return ExceptionResponse
                .builder()
                .errorMsg(exp.getMessage())
                .build();
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handle(IllegalArgumentException exp) {
        return ExceptionResponse
                .builder()
                .errorMsg("Bad request. Invalid argument: " + exp.getMessage())
                .build();
    }
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handle(NumberFormatException exp) {
        return ExceptionResponse
                .builder()
                .errorMsg("Bad request. Invalid number format: " + exp.getMessage())
                .build();
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handle(Exception exp) {
        // log
        log.error("Error occurred: ", exp);
        return ExceptionResponse
                .builder()
                .errorMsg("Oups, an error has occurred. Please contact tbe admin")
                .build();
    }
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handle(NullPointerException exp) {
        return ExceptionResponse
                .builder()
                .errorMsg("Internal server error. Null pointer exception: " + exp.getMessage())
                .build();
    }
    @ExceptionHandler(UnsupportedOperationException.class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public ExceptionResponse handle(UnsupportedOperationException exp) {
        return ExceptionResponse
                .builder()
                .errorMsg("Not implemented. " + exp.getMessage())
                .build();
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ExceptionResponse> handleSignatureException(SignatureException ex) {
        ExceptionResponse response = ExceptionResponse.builder()
                .errorMsg("JWT signature verification failed")
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(MalformedJwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleMalformedJwtException(MalformedJwtException ex) {
        // Log the exception
        ex.printStackTrace();

        // Customize the error response message if needed
        String errorMessage = "Invalid JWT token format";

        // You can return a custom response entity with the error message
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }




}
/*
-This class is a Spring RestControllerAdvice, which means it globally handles exceptions for all controllers in your application.
-It contains several methods annotated with @ExceptionHandler to handle specific types of exceptions.
-Each method returns an ExceptionResponse, providing details about the exception and, in some cases, additional information.

 */
