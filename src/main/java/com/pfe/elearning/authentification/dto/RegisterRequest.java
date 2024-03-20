// RegisterRequest.java
package com.pfe.elearning.authentification.dto;

import com.pfe.elearning.role.RoleType;
import jakarta.validation.constraints.*;
import lombok.Data;


import java.util.List;

@Data
public class RegisterRequest {
    @NotNull(message = "First name is mandatory")
    @NotBlank(message = "First name is mandatory")
    private String firstname;
    @NotNull(message = "Last name is mandatory")
    @NotBlank(message = "Last name is mandatory")
    private String lastname;
    @NotNull(message = "Email name is mandatory")
    @NotBlank(message = "Email name is mandatory")
    @Email(message = "Email is not valid")
    private String email;
    @NotNull(message = "Email name is mandatory")
    @NotBlank(message = "Email name is mandatory")
    @Size(min = 4, max = 16, message = "Password should be between 4 and 16 chars")
    private String password;
    //private RoleType role;
    private String role;

   /*
   public String getFullName() {

        return getFirstname() + " " + getLastname();
    }

*/
}
