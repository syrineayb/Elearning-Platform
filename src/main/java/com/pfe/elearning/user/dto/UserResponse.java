package com.pfe.elearning.user.dto;

import com.pfe.elearning.role.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class UserResponse {
    private Integer id;
    private String username;
    private String email;
    private LocalDateTime createdAt;
    private boolean enabled;
    private boolean active;
    private List<RoleType> roles;
}
