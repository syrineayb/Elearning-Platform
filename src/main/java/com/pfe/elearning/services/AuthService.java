package com.pfe.elearning.services;

import com.pfe.elearning.dto.AuthMapper;
import com.pfe.elearning.dto.AuthRequest;
import com.pfe.elearning.dto.AuthResponse;
import com.pfe.elearning.dto.RegisterRequest;
import com.pfe.elearning.entity.Role;
import com.pfe.elearning.entity.RoleEnum;
import com.pfe.elearning.entity.User;
import com.pfe.elearning.repository.RoleRepository;
import com.pfe.elearning.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthMapper mapper;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;

    public void register(RegisterRequest request) {
        var student = mapper.toStudent(request);
        var encryptedPassword = passwordEncoder.encode(student.getPassword());
        student.setPassword(encryptedPassword);
        // get or create student role
        Role studentRole = roleRepository.findByName(RoleEnum.ROLE_CANDIDATE)
                .orElseGet(() -> {
                    var newStudentRole = Role.builder()
                            .name(RoleEnum.ROLE_CANDIDATE)
                            .build();
                    return roleRepository.save(newStudentRole);
                });

        student.setRoles(List.of(studentRole));

        studentRepository.save(student);
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