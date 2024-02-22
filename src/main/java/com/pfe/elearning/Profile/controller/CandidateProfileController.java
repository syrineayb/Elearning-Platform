package com.pfe.elearning.Profile.controller;

import com.pfe.elearning.Profile.dto.Request.CandidateProfileRequest;
import com.pfe.elearning.Profile.dto.Response.CandidateProfileResponse;
import com.pfe.elearning.Profile.service.CandidateProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidates")
@RequiredArgsConstructor
public class CandidateProfileController {

    private final CandidateProfileService candidateProfileService;

    @GetMapping("/profile/{candidateId}")
    @PreAuthorize("hasAnyRole('ADMIN','CANDIDATE')")
    public ResponseEntity<CandidateProfileResponse> getCandidateProfile(@PathVariable Long candidateId) {
        CandidateProfileResponse response = candidateProfileService.getCandidateProfile(candidateId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{candidateId}/profile")
    @PreAuthorize("hasAnyRole('CANDIDATE')")
    public ResponseEntity<CandidateProfileResponse> updateCandidateProfile(@PathVariable Long candidateId,
                                                                           @RequestBody CandidateProfileRequest profileRequest) {
        CandidateProfileResponse response = candidateProfileService.updateCandidateProfile(candidateId, profileRequest);
        return ResponseEntity.ok(response);
    }
}