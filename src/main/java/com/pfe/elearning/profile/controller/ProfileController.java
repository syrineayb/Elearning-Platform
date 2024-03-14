package com.pfe.elearning.profile.controller;

import com.pfe.elearning.profile.dto.ProfileRequest;
import com.pfe.elearning.profile.dto.ProfileResponse;
import com.pfe.elearning.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/{userId}")
   @PreAuthorize("hasAnyRole('ADMIN','CANDIDATE')")
    public ResponseEntity<ProfileResponse> getUserProfile(@PathVariable Long userId) {
        ProfileResponse response = profileService.getUserProfile(userId);
        return ResponseEntity.ok(response);
    }
/*
    @PutMapping("/{userId}/profile")
    @PreAuthorize("hasAnyRole('CANDIDATE')")
    public ResponseEntity<ProfileResponse> updateUserProfile(@PathVariable Long candidateId,
                                                                  @RequestBody ProfileRequest profileRequest) {
        ProfileResponse response = userProfileService.updateUserProfile(userId, profileRequest);
        return ResponseEntity.ok(response);
    }
 */
@PutMapping("/{userId}")
@PreAuthorize("hasAnyRole('CANDIDATE','INSTRUCTOR')")
public ResponseEntity<ProfileResponse> updateUserProfile(
        @PathVariable Long userId,
        @RequestBody ProfileRequest profileRequest) {

    // Assuming you have a service method to update the profile
    ProfileResponse updatedProfile = profileService.updateUserProfile(userId, profileRequest);

    // Check if the update was successful
    if (updatedProfile != null) {
        return ResponseEntity.ok(updatedProfile);
    } else {
        return ResponseEntity.notFound().build();
    }
}
}