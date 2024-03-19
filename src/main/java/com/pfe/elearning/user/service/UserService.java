package com.pfe.elearning.user.service;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.user.dto.ChangePasswordRequest;
import com.pfe.elearning.user.dto.UserRequest;
import com.pfe.elearning.user.dto.UserResponse;
import com.pfe.elearning.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface UserService {
    // Method to create a new user
    void create(UserRequest request);

    // Method to retrieve all users
    PageResponse<UserResponse> findAll(int page, int size);
    // Method to find a user by ID
    UserResponse findById(Integer id);
    void update(Integer userId, UserRequest request);

    // Method to delete a user by ID
    void delete(Integer id);

    // Method to find all users by state (active or inactive) with pagination
    PageResponse<UserResponse> findAllUsersByState(boolean active, int page, int size);

    // Method to change user password
    void changePassword(ChangePasswordRequest request, Principal connectedUser);

    // Method to get a user by email
    User getUserByEmail(String userEmail);
}
