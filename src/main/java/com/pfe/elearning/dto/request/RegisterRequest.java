// RegisterRequest.java
package com.pfe.elearning.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.util.List;

@Data
public class RegisterRequest {
    @NotBlank(message = "Firstname must not be blank")
    private String firstname;
    @NotBlank(message = "Lastname must not be blank")
    private String lastname;
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email is not well formatted")
    private String email;
    @NotBlank(message = "Password must not be blank")
    private String password;
    @NotBlank(message = "Roles must not be Blank")
    private String roleName;;
}
