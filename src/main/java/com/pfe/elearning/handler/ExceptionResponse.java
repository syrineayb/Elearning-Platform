package com.pfe.elearning.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class ExceptionResponse {

    private String errorMsg;
    private String errorSource;
    private Set<String> validationErrors;


}
/*
-This is a simple DTO (Data Transfer Object) class used to represent the details of an exception in a structured format.
-It includes fields such as errorMsg (the main error message), errorSource (source of the error, used in ObjectValidationException), and validationErrors (set of validation errors in case of ObjectValidationException).

 */