package com.pfe.elearning.authentification.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pfe.elearning.authentification.dto.AuthenticationRequest;
import com.pfe.elearning.authentification.dto.RegisterRequest;
import com.pfe.elearning.authentification.dto.AuthenticationResponse;
import com.pfe.elearning.profile.entity.Profile;
import com.pfe.elearning.profile.repository.ProfileRepository;
import com.pfe.elearning.security.JwtService;
import com.pfe.elearning.role.Role;
import com.pfe.elearning.role.RoleType;
import com.pfe.elearning.token.Token;
import com.pfe.elearning.token.TokenType;
import com.pfe.elearning.token.repository.TokenRepository;
import com.pfe.elearning.user.entity.User;
import com.pfe.elearning.role.RoleRepository;
import com.pfe.elearning.user.repository.UserRepository;
import com.pfe.elearning.validator.ObjectsValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final ProfileRepository profileRepository;
    private final ObjectsValidator<RegisterRequest> validator;
    private final TokenRepository tokenRepository;
    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        validator.validate(request);
        RoleType requestedRole = request.getRole();
        Role userRole = roleRepository.findByName(requestedRole.name())
                .orElseGet(() -> roleRepository.save(Role.builder().name(requestedRole.name()).build()));

        User user = User.builder()
                //.firstname(request.getFirstName())
                .username(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(true)
                .roles(List.of(userRole))
                .build();

        User savedUser = userRepository.save(user);

        userRole.setUsers(new ArrayList<>(List.of(savedUser)));
        roleRepository.save(userRole);

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRoles());
        claims.put("active", user.isActive());

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        saveUserToken(savedUser, jwtToken);


        Profile profile = new Profile();
        profile.setUser(user);
        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setEmail(request.getEmail());
        profileRepository.save(profile);
        return buildAuthenticationResponse(savedUser, jwtToken, refreshToken);
    }

    private AuthenticationResponse buildAuthenticationResponse(User user, String jwtToken, String refreshToken) {
        return AuthenticationResponse.builder()
                .username(user.getUsername())
             //   .username(user.getFirstname() + " " + user.getLastname())
                .userId(user.getId())
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private boolean isValidRole(String roleName) {
        for (RoleType roleType : RoleType.values()) {
            if (roleType.name().equals(roleName)) {
                return true;
            }
        }
        return false;
    }
       public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

           User user = userRepository.findByEmail(request.getEmail())
                   .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRoles());
        claims.put("active", user.isActive());

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
           return buildAuthenticationResponse(user, jwtToken, refreshToken);
       }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (!validUserTokens.isEmpty()) {
            validUserTokens.forEach(token -> {
                token.setExpired(true);
                token.setRevoked(true);
            });
            tokenRepository.saveAll(validUserTokens);
        }
    }
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
