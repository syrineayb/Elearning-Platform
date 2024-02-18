package com.pfe.elearning.service;

import com.pfe.elearning.dto.mapper.AuthMapper;
import com.pfe.elearning.dto.request.AuthRequest;
import com.pfe.elearning.dto.response.AuthResponse;
import com.pfe.elearning.dto.request.RegisterRequest;
import com.pfe.elearning.entity.Role;
import com.pfe.elearning.entity.RoleEnum;
import com.pfe.elearning.entity.User;
import com.pfe.elearning.repository.RoleRepository;
import com.pfe.elearning.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthMapper mapper;
    private final CandidateRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;

    public void register(RegisterRequest request) {
        var candidate = mapper.toCandidate(request);
        var encryptedPassword = passwordEncoder.encode(candidate.getPassword());
        candidate.setPassword(encryptedPassword);
        // get or create student role
        Role studentRole = roleRepository.findByName(RoleEnum.ROLE_CANDIDATE)
                .orElseGet(() -> {
                    var newCandidateRole = Role.builder()
                            .name(RoleEnum.ROLE_CANDIDATE)
                            .build();
                    return roleRepository.save(newCandidateRole);
                });

        candidate.setRoles(List.of(studentRole));

        studentRepository.save(candidate);
    }

    public AuthResponse authenticate(AuthRequest request) {
        Authentication authenticatedUser = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var userDetails = (User) authenticatedUser.getPrincipal();
        HashMap<String, Object> claims = new HashMap<>();
        var authorities = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        claims.put("firstname", userDetails.getFirstname());
        claims.put("authorities", authorities);
        var jwtToken = jwtService.generateToken(claims, userDetails);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}