// AuthResponse.java
package com.pfe.elearning.authentification.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
   // private LocalDateTime createdAt;
    private String token;
    private Long userId;
    private String username;
   private LocalDateTime createdAt;
}
