package com.pfe.elearning.user.controller;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.user.dto.ChangePasswordRequest;
import com.pfe.elearning.user.dto.UserRequest;
import com.pfe.elearning.user.dto.UserResponse;
import com.pfe.elearning.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users")
public class userController {
    private final UserService userService;
    @Operation(
            description = "Saves a user to the database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User successfully created"),
                    @ApiResponse(responseCode = "403", description = "Missing or invalid JWT token")
            }
    )
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> save(@RequestBody UserRequest userRequest) {
        userService.create(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<PageResponse<UserResponse>> findAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "20", required = false) int size
    ) {
        return ResponseEntity.ok(userService.findAll(page, size));
    }
    @GetMapping("/{user-id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserResponse> findById(
            @PathVariable("user-id") Integer id
    ) {
        return ResponseEntity.ok(userService.findById(id));
    }
    @PatchMapping("/changePassword")
    @PreAuthorize("hasAnyAuthority('ROLE_CANDIDATE', 'ROLE_INSTRUCTOR', 'ROLE_ADMIN')")
    public ResponseEntity<String> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok("Password changed successfully."); // Custom response message
    }

    @PutMapping("/{user-id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> updateUser(
            @PathVariable("user-id") Integer userId,
            @RequestBody UserRequest userRequest
    ) {
        userService.update(userId, userRequest);
        return ResponseEntity.ok("User "+userId+" updated successfully");
    }
    @DeleteMapping("/{user-id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> deleteUser(
            @PathVariable("user-id") Integer userId
    ) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }

}
    /*@PutMapping("/changepassword/{userId}")
    public ResponseEntity<String> updatePassword(
            @PathVariable Long userId,
            @RequestBody UserRequest userRequest) {
        return userService.updatePassword(userId, userRequest);
    }

     */

