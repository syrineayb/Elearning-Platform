package com.pfe.elearning.admin;
import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.user.dto.UserResponse;
import com.pfe.elearning.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Tag(name = "Admin")
@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminController {

    private final UserService userService;

    @GetMapping("/users/inactive")
    public ResponseEntity<PageResponse<UserResponse>> findAllNonActiveUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<UserResponse> nonActiveUsers = userService.findAllUsersByState(false, page, size);
        return ResponseEntity.ok(nonActiveUsers);
    }

    @GetMapping("/users/active")
    public ResponseEntity<PageResponse<UserResponse>> findAllActiveUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<UserResponse> activeUsers = userService.findAllUsersByState(true, page, size);
        return ResponseEntity.ok(activeUsers);
    }
}