package com.pfe.elearning.authentification.service;

import com.pfe.elearning.authentification.dto.request.AuthRequest;
import com.pfe.elearning.authentification.dto.request.RegisterRequest;
import com.pfe.elearning.authentification.dto.response.AuthResponse;
import com.pfe.elearning.user.entity.Role;
import com.pfe.elearning.user.entity.RoleEnum;
import com.pfe.elearning.user.entity.User;
import com.pfe.elearning.user.repository.RoleRepository;
import com.pfe.elearning.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public AuthResponse register(RegisterRequest request) {
        Role role = fetchRole(request.getRoleName());
        User user = createUserFromRequest(request, role);
        User savedUser = userRepository.save(user);
        String token = jwtService.generateToken(savedUser.getEmail(), Collections.singletonList(role.getName().name()));
        return AuthResponse.builder().token(token).build();
    }

    private Role fetchRole(String roleName) {
        return roleRepository.findByName(RoleEnum.valueOf(roleName))
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName));
    }

    private User createUserFromRequest(RegisterRequest request, Role role) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        return User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(encodedPassword)
                .enabled(true)
                .role(role)
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails.getUsername(), Collections.emptyList());
        return AuthResponse.builder().token(token).build();
    }
}
