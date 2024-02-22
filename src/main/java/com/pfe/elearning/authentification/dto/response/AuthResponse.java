// AuthResponse.java
package com.pfe.elearning.authentification.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AuthResponse {
    private String token;
    private LocalDateTime createdAt;
}
