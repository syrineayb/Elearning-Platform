package com.pfe.elearning.handler;

import com.pfe.elearning.exception.DuplicateEntryException;
import com.pfe.elearning.exception.ObjectValidationException;
import com.pfe.elearning.exception.OperationNonPermittedException;
import com.pfe.elearning.exception.UnauthorizedAccessException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;
import java.util.HashSet;
import java.util.Set;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({
          //  ObjectValidationException.class,
            IllegalArgumentException.class,
            NumberFormatException.class,
            NullPointerException.class,
            UnsupportedOperationException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleBadRequestException(Exception exp) {
        String errorMessage =
                //exp instanceof ObjectValidationException ? "Object not valid" :
                exp instanceof IllegalArgumentException ? "Bad request. Invalid argument: " + exp.getMessage() :
                        exp instanceof NullPointerException ? "Internal server error. Null pointer exception: " + exp.getMessage() :
                                        exp instanceof UnsupportedOperationException ? "Not implemented. " + exp.getMessage() :
                                                "Bad request";

        return ExceptionResponse.builder()
                .errorMsg(errorMessage)
                .build();
    }

    @ExceptionHandler({EntityNotFoundException.class, UsernameNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleNotFoundException(Exception exp) {
        return ExceptionResponse.builder()
                .errorMsg(exp.getMessage())
                .build();
    }

    @ExceptionHandler({BadCredentialsException.class, DisabledException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleAuthenticationException(Exception exp) {
        String errorMessage = exp instanceof BadCredentialsException ? "Username and / or password is incorrect" :
                "The user is disabled. Please contact the admin";

        return ExceptionResponse.builder()
                .errorMsg(errorMessage)
                .build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse handleAccessDeniedException(AccessDeniedException exp) {
        return ExceptionResponse.builder()
                .errorMsg("Access denied")
                .build();
    }

    @ExceptionHandler(OperationNonPermittedException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ExceptionResponse handleOperationNonPermittedException(OperationNonPermittedException exp) {
        return ExceptionResponse.builder()
                .errorMsg(exp.getMessage())
                .build();
    }

    @ExceptionHandler({SignatureException.class, MalformedJwtException.class})
    public ResponseEntity<ExceptionResponse> handleJwtException(Exception exp) {
        String errorMessage = exp instanceof SignatureException ? "JWT signature verification failed" :
                "Invalid JWT token format";

        ExceptionResponse response = ExceptionResponse.builder()
                .errorMsg(errorMessage)
                .build();
        HttpStatus status = exp instanceof SignatureException ? HttpStatus.UNAUTHORIZED : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler({UnauthorizedAccessException.class, DataIntegrityViolationException.class, DuplicateEntryException.class})
    public ResponseEntity<String> handleCustomExceptions(Exception exp) {
        HttpStatus status = exp instanceof UnauthorizedAccessException ? HttpStatus.UNAUTHORIZED :
                exp instanceof DataIntegrityViolationException || exp instanceof DuplicateEntryException ?
                        HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(exp.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleGenericException(Exception exp) {
        // log
        log.error("Error occurred: ", exp);
        return ExceptionResponse.builder()
                .errorMsg("Oups, an error has occurred. Please contact the admin")
                .build();
    }
    @ExceptionHandler(ObjectValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleObjectValidationException(ObjectValidationException ex) {
        // Extract validation errors from the exception
        Set<String> validationErrors = ex.getViolations();

        // Return ExceptionResponse with errorMsg and validationErrors
        return ExceptionResponse.builder()
                .errorMsg("Object not valid")
                .validationErrors(validationErrors)
                .build();
    }



}
/*
-This class is a Spring RestControllerAdvice, which means it globally handles exceptions for all controllers in your application.
-It contains several methods annotated with @ExceptionHandler to handle specific types of exceptions.
-Each method returns an ExceptionResponse, providing details about the exception and, in some cases, additional information.

 */
