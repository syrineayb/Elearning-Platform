package com.pfe.elearning.user.service.serviceImpl;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.user.dto.mapper.UserMapper;
import com.pfe.elearning.user.dto.request.UserRequest;
import com.pfe.elearning.user.dto.response.UserResponse;
import com.pfe.elearning.user.entity.User;
import com.pfe.elearning.user.repository.UserRepository;
import com.pfe.elearning.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public PageResponse<UserResponse> findAll(int page, int size) {
        var pageResult = this.userRepository.findAll(PageRequest.of(page, size));
        System.out.println("Total Elements: " + pageResult.getTotalElements());
        System.out.println("Number of Elements: " + pageResult.getNumberOfElements());
        System.out.println("Content Size: " + pageResult.getContent().size());
        return PageResponse.<UserResponse>builder()
                .content(
                        pageResult.getContent()
                                .stream()
                                .map(userMapper::toUserResponseDto)
                                .toList()
                )
                .totalPages(pageResult.getTotalPages())
                .build();
    }


    @Override
    public ResponseEntity<String> updatePassword(Long userId, UserRequest userRequest) {
        // Ensure UserRequest is not null and contains necessary fields
        if (userRequest == null || userRequest.getOldPassword() == null || userRequest.getNewPassword() == null) {
            return ResponseEntity.badRequest().body("Old and new passwords are required");
        }

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // Extract old and new passwords from the UserRequest
        String oldPassword = userRequest.getOldPassword();
        String newPassword = userRequest.getNewPassword();

        // Check if the old password matches
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return ResponseEntity.badRequest().body("Old password is incorrect");
        }

        // Update the password
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return ResponseEntity.ok("Password updated successfully");
    }
}
