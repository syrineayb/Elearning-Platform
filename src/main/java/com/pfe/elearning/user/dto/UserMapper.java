package com.pfe.elearning.user.dto;

import com.pfe.elearning.user.entity.User;
import org.springframework.stereotype.Service;

@Service

public class UserMapper {
    public UserResponse toUserResponseDto(User user) {
        return UserResponse.builder()
               // .firstName(user.getFirstname())
               // .lastName(user.getLastname())//
                .username(user.getUsername())
                .email(user.getEmail())
                .active(user.isActive())
              .createdAt(user.getCreatedAt())
                .build();
    }
    public User toUser(UserRequest request) {
        return User.builder()
                .id(request.getId())
               // .firstname(request.getFirstname())
                //.lastname(request.getLastname())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }
}
