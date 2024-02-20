package com.pfe.elearning.candidate.dto.mapper;

import com.pfe.elearning.admin.entity.Admin;
import com.pfe.elearning.authentification.dto.request.RegisterRequest;
import com.pfe.elearning.authentification.dto.response.AuthResponse;
import com.pfe.elearning.candidate.entity.Candidate;
import com.pfe.elearning.trainer.entity.Trainer;
import com.pfe.elearning.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public class AuthMapper {

    public Candidate toCandidate(RegisterRequest request) {
        Candidate candidate = new Candidate();
        candidate.setFirstname(request.getFirstname());
        candidate.setLastname(request.getLastname());
        candidate.setEmail(request.getEmail());
        // FIXME: Don't set the password directly here, handle password hashing properly
        candidate.setPassword(request.getPassword());
        candidate.setEnabled(true);
        return candidate;
    }

    public Admin toAdmin(RegisterRequest request) {
        Admin admin = new Admin();
        admin.setFirstname(request.getFirstname());
        admin.setLastname(request.getLastname());
        admin.setEmail(request.getEmail());
        // FIXME: Don't set the password directly here, handle password hashing properly
        admin.setPassword(request.getPassword());
        admin.setEnabled(true);
        return admin;
    }



    public Trainer toTrainer(RegisterRequest request) {
        Trainer trainer = new Trainer();
        trainer.setFirstname(request.getFirstname());
        trainer.setLastname(request.getLastname());
        trainer.setEmail(request.getEmail());
        // FIXME: Don't set the password directly here, handle password hashing properly
        trainer.setPassword(request.getPassword());
        trainer.setEnabled(true);
        return trainer;
    }



    public AuthResponse mapUserToAuthResponse(User savedUser) {
        return AuthResponse.builder()
                .token(savedUser.getId().toString()) // Assuming token is user ID for simplicity
                .build();
    }

}