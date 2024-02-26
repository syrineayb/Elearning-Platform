package com.pfe.elearning.user.controller;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.user.dto.request.UserRequest;
import com.pfe.elearning.user.dto.response.UserResponse;
import com.pfe.elearning.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class userController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<PageResponse<UserResponse>> findAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "20", required = false) int size
    ) {
        return ResponseEntity.ok(userService.findAll(page, size));
    }
    @PutMapping("/changepassword/{userId}")
    public ResponseEntity<String> updatePassword(
            @PathVariable Long userId,
            @RequestBody UserRequest userRequest) {
        return userService.updatePassword(userId, userRequest);
    }
}
