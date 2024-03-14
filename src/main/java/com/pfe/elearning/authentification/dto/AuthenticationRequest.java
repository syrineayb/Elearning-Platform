// AuthRequest.java
package com.pfe.elearning.authentification.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class AuthenticationRequest {
    @NotBlank(message = "Email must not be blank")
    private String email;
    @NotBlank(message = "Password must not be blank")
    private String password;
}
