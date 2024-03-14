// AuthResponse.java
package com.pfe.elearning.authentification.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {
   // private LocalDateTime createdAt;
   @JsonProperty("access_token")
   private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    //private String token;
    private Integer userId;
    private String username;
   private LocalDateTime createdAt;
}
