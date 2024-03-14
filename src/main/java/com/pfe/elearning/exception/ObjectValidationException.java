package com.pfe.elearning.exception;

import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ObjectValidationException extends RuntimeException {

    private final Set<String> violations; /* validation error messages */
    private final String violationSource; /* source of the violation */
}
/*
-This exception is thrown when an object fails validation.
 */