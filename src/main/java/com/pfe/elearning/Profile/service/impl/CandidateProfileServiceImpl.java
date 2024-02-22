package com.pfe.elearning.Profile.service.impl;

import com.pfe.elearning.Profile.dto.Mapper.CandidateMapper;
import com.pfe.elearning.Profile.dto.Request.CandidateProfileRequest;
import com.pfe.elearning.Profile.dto.Response.CandidateProfileResponse;
import com.pfe.elearning.Profile.service.CandidateProfileService;
import com.pfe.elearning.candidate.entity.Candidate;
import com.pfe.elearning.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CandidateProfileServiceImpl implements CandidateProfileService {

    private final CandidateRepository candidateRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CandidateProfileServiceImpl(CandidateRepository candidateRepository, PasswordEncoder passwordEncoder) {
        this.candidateRepository = candidateRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public CandidateProfileResponse getCandidateProfile(Long candidateId) {
        Optional<Candidate> candidateOptional = candidateRepository.findById(candidateId);
        if (candidateOptional.isPresent()) {
            Candidate candidate = candidateOptional.get();
            // Now you have the candidate object, you can access all its data
            CandidateProfileResponse response = new CandidateProfileResponse();
            response.setFirstName(candidate.getFirstname());
            response.setLastName(candidate.getLastname());
            response.setAge(candidate.getAge());
            // Add mapping for other fields if needed
            return response;
        } else {
            throw new IllegalArgumentException("Candidate not found with id: " + candidateId);
        }
    }


    @Override
    public CandidateProfileResponse updateCandidateProfile(Long candidateId, CandidateProfileRequest profileRequest) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new IllegalArgumentException("Candidate not found with id: " + candidateId));

        // Check if the old password matches the current password
        if (passwordEncoder.matches(String.valueOf(profileRequest.getOldPassword()), candidate.getPassword())) {
            // Update the candidate's profile with the new password
            candidate.setPassword(passwordEncoder.encode(String.valueOf(profileRequest.getNewPassword())));
            candidate.setAge(profileRequest.getAge());
            candidate.setFirstname(profileRequest.getFirstName());
            candidate.setLastname(profileRequest.getLastName());
            candidateRepository.save(candidate);
            return CandidateMapper.toCandidateProfileResponse(candidate);
        } else {
            // If old password doesn't match, throw an exception or handle it accordingly
            throw new IllegalArgumentException("Old password is incorrect");
        }
    }
}
