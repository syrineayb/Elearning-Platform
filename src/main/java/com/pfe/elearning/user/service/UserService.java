package com.pfe.elearning.user.service;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.user.dto.response.UserResponse;

public interface UserService {
    PageResponse<UserResponse> findAll(int page, int size);

}
