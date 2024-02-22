// AuthController.java
package com.pfe.elearning.authentification.controller;

import com.pfe.elearning.authentification.dto.request.AuthRequest;
import com.pfe.elearning.authentification.dto.request.RegisterRequest;
import com.pfe.elearning.authentification.dto.response.AuthResponse;
import com.pfe.elearning.authentification.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody @Valid AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
