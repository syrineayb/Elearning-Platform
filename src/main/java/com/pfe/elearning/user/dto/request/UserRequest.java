package com.pfe.elearning.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserRequest {
    /*private String firstName;
    private String lastName;

    private String email;
    private String password;

     */
    @NotNull(message = "Old password is mandatory")
    @NotBlank(message = "Old password is mandatory")
    private String oldPassword;

    @NotNull(message = "New password is mandatory")
    @NotBlank(message = "New password is mandatory")
    private String newPassword;

   // private LocalDateTime createdAt;

}
