package com.pfe.elearning.authentification.service;

import com.pfe.elearning.authentification.dto.request.AuthRequest;
import com.pfe.elearning.authentification.dto.request.RegisterRequest;
import com.pfe.elearning.authentification.dto.response.AuthResponse;
import com.pfe.elearning.profile.repository.ProfileRepository;
import com.pfe.elearning.profile.entity.Profile;
import com.pfe.elearning.role.entity.Role;
import com.pfe.elearning.role.entity.RoleType;
import com.pfe.elearning.role.entity.repository.RoleRepository;
import com.pfe.elearning.user.entity.User;
import com.pfe.elearning.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(true)
                .build();

        List<Role> userRoles = new ArrayList<>();

        // Check if role names are provided in the request
        if (request.getRoleName() != null && !request.getRoleName().isEmpty()) {
            for (String roleName : request.getRoleName()) {
                // Validate against RoleType enum
                if (!isValidRole(roleName)) {
                    throw new IllegalArgumentException("Invalid role: " + roleName);
                }

                Role role = roleRepository.findByName(roleName)
                        .orElse(Role.builder().name(roleName).build());

                if (role.getId() == null) {
                    role = roleRepository.save(role);
                }

                userRoles.add(role);
            }
        } else {
            // If no roles are provided, default to ROLE_USER
            Role defaultRole = roleRepository.findByName(RoleType.ROLE_USER.name())
                    .orElse(Role.builder().name(RoleType.ROLE_USER.name()).build());

            if (defaultRole.getId() == null) {
                defaultRole = roleRepository.save(defaultRole);
            }

            userRoles.add(defaultRole);
        }

        user.setRoles(userRoles);
        var savedUser = userRepository.save(user);

        for (Role role : userRoles) {
            role.setUsers(new ArrayList<>(List.of(savedUser)));
            roleRepository.save(role);
        }
        Profile profile = new Profile();
        profile.setUser(user);
       // profile.setUser(user);
        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setEmail(request.getEmail());
        profileRepository.save(profile);

        return getAuthResponse(user, savedUser);
    }

    // Validate if the role exists in the RoleType enum
    private boolean isValidRole(String roleName) {
        for (RoleType roleType : RoleType.values()) {
            if (roleType.name().equals(roleName)) {
                return true;
            }
        }
        return false;
    }




    private AuthResponse getAuthResponse(User user, User savedUser) {
        var claims = new HashMap<String, Object>();
        claims.put("role", user.getRoles());
        claims.put("active", user.isActive());
        var jwtToken = jwtService.generateToken(savedUser, claims);

        return AuthResponse.builder()
                .createdAt(savedUser.getCreatedAt())  // Set the createdAt field
                .token(jwtToken)
                .username(user.getFirstname() + " " + user.getLastname())
                .userId(savedUser.getId())
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var claims = new HashMap<String, Object>();
        claims.put("role", user.getRoles());
        claims.put("active", user.isActive());
        var jwtToken = jwtService.generateToken(user, claims);

        return AuthResponse.builder()
                .token(jwtToken)
                .username(user.getFirstname() + " " + user.getLastname())
                .userId(user.getId())
                .userId(user.getId())
                .build();
    }
}
