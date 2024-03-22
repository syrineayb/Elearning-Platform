// AuthController.java
package com.pfe.elearning.authentification.controller;

import com.pfe.elearning.authentification.dto.AuthenticationRequest;
import com.pfe.elearning.authentification.dto.RegisterRequest;
import com.pfe.elearning.authentification.dto.AuthenticationResponse;
import com.pfe.elearning.authentification.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest request) {
        AuthenticationResponse response = authenticationService.register(request);
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+request.getRole());
        return ResponseEntity.ok(response);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }
}
