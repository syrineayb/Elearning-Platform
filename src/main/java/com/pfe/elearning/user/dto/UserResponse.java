package com.pfe.elearning.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class UserResponse {
    private String username;
    //private String lastName;
    private String email;

   // @Column(name = "created_at", nullable = false)
   private LocalDateTime createdAt;
    private boolean active;
}
