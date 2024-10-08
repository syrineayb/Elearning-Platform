package com.pfe.elearning.profile.controller;

import com.pfe.elearning.profile.dto.ProfileRequest;
import com.pfe.elearning.profile.dto.ProfileResponse;
import com.pfe.elearning.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('CANDIDATE','INSTRUCTOR','ADMIN')")
    public ResponseEntity<ProfileResponse> getCurrentUserProfile() {
        ProfileResponse profile = profileService.getCurrentUserProfile();
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('CANDIDATE','INSTRUCTOR','ADMIN')")
    public ResponseEntity<ProfileResponse> updateProfile(@RequestBody ProfileRequest profileRequest) {
        try {
            // Pass both the file and profile request to the service for updating the profile
            ProfileResponse updatedProfile = profileService.updateProfile(profileRequest);
            return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}