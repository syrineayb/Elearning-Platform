// AuthRequest.java
package com.pfe.elearning.authentification.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class AuthenticationRequest {
    @NotNull(message = "Email is mandatory")
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    private String email;
    @NotNull(message = "Password is mandatory")
    @NotBlank(message = "Password is mandatory")
    private String password;
}
