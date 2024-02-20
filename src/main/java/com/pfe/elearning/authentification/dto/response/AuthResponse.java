// AuthResponse.java
package com.pfe.elearning.authentification.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String token;
}
