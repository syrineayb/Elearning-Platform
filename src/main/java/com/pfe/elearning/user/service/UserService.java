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
    Integer create(UserRequest request);
    PageResponse<UserResponse> findAll(int page, int size);
    UserResponse findById(Integer id);
    void delete(Integer id);
    PageResponse<UserResponse> findAllUsersByState(boolean active, int page, int size);
    void changePassword(ChangePasswordRequest request, Principal connectedUser);

    User getUserByEmail(String publisherUsername);

}