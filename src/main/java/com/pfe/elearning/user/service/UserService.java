package com.pfe.elearning.user.service;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.user.dto.request.UserRequest;
import com.pfe.elearning.user.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    PageResponse<UserResponse> findAll(int page, int size);
    ResponseEntity<String> updatePassword(Long userId, UserRequest userRequest);

}
