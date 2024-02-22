package com.pfe.elearning.user.dto.mapper;

import com.pfe.elearning.user.dto.response.UserResponse;
import com.pfe.elearning.user.entity.User;
import org.springframework.stereotype.Service;

@Service

public class UserMapper {
    public UserResponse toUserResponseDto(User user) {
        return UserResponse.builder()
                .firstName(user.getFirstname())
                .lastName(user.getLastname())//
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
