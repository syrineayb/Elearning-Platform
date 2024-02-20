// AuthRequest.java
package com.pfe.elearning.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class AuthRequest {
    @NotBlank(message = "Email must not be blank")
    private String email;
    @NotBlank(message = "Password must not be blank")
    private String password;
}
