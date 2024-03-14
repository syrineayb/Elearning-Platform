package com.pfe.elearning.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserRequest {
    private Integer id;
 /*
    @NotNull(message = "First name is mandatory")
    private String firstname;
    @NotNull(message = "Last name is mandatory")
    private String lastname;

  */
 @NotNull(message = "Username is mandatory")
 private String username;
    @NotNull(message = "Email name is mandatory")
    @Email(message = "Email is not valid")
    private String email;
    @NotNull(message = "Email name is mandatory")
    @Size(min = 4, max = 16, message = "Password should be between 4 and 16 chars")
    private String password;
/*
    @NotNull(message = "Old password is mandatory")
    @Size(min = 4, max = 16, message = "Password should be between 4 and 16 chars")
    private String oldPassword;

    @NotNull(message = "New password is mandatory")
    @Size(min = 4, max = 16, message = "Password should be between 4 and 16 chars")
    private String newPassword;


 */
   // private LocalDateTime createdAt;
  /* @NotNull(message = "Email name is mandatory")
   @Size(min = 4, max = 16, message = "Password should be between 4 and 16 chars")
   private String password;

   */
}
