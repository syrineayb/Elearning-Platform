package com.pfe.elearning.profile.controller;

import com.pfe.elearning.profile.dto.Request.ProfileRequest;
import com.pfe.elearning.profile.dto.Response.ProfileResponse;
import com.pfe.elearning.profile.entity.Profile;
import com.pfe.elearning.profile.repository.ProfileRepository;
import com.pfe.elearning.profile.service.ProfileService;
import com.pfe.elearning.profile.service.ProfileService;
import com.pfe.elearning.user.entity.User;
import com.pfe.elearning.user.repository.UserRepository;
import com.pfe.elearning.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    @GetMapping("/{userId}")
   // @PreAuthorize("hasAnyRole('ADMIN','CANDIDATE')")
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
public ResponseEntity<ProfileResponse> updateUserProfile(
        @PathVariable Long userId,
        @RequestBody ProfileRequest profileRequest) {
    User user = userRepository.findById(userId).orElse(null);
    if (user == null) {
        return ResponseEntity.notFound().build();
    }
    Profile profile = profileRepository.findById(userId) .orElseThrow(() -> new IllegalArgumentException("profile not found with id: " + userId));

    profile.setFirstName(profileRequest.getFirstName());
    profile.setLastName(profileRequest.getLastName());
    profile.setEmail(profileRequest.getEmail());
    profile.setImage(profileRequest.getImage());
    profile.setDescription(profileRequest.getDescription());
    profile.setPhoneNumber(profileRequest.getPhoneNumber());
    profile.setDateOfBirth(profileRequest.getDateOfBirth());
    profile.setCountry(profileRequest.getCountry());
    profile.setCurrentJob(profileRequest.getCurrentJob());
    profile.setExperience(profileRequest.getExperience());
    profile.setDegreeOfEducation(profileRequest.getDegreeOfEducation());
    profile.setCertificates(profileRequest.getCertificates());
Profile updatedProfile=profileRepository.save(profile);
    // Assuming you have a service method to update the profile
    // Update user entity with new profile information
    user.setFirstname(profileRequest.getFirstName());
    user.setLastname(profileRequest.getLastName());
    user.setEmail(profileRequest.getEmail());
    // Update other profile-related attributes here

    // Save the updated user entity
    userRepository.save(user);
    ProfileResponse profileResponse = ProfileResponse.builder()
            .profileId(updatedProfile.getId())
            .firstName(updatedProfile.getFirstName())
            .lastName(updatedProfile.getLastName())
            .email(updatedProfile.getEmail())
            .country(updatedProfile.getCountry())
            .currentJob(updatedProfile.getCurrentJob())
            .experience(updatedProfile.getExperience())
            .degreeOfEducation(updatedProfile.getDegreeOfEducation())
            .certificates(updatedProfile.getCertificates())
            // Add other fields as needed
            .build();

    return ResponseEntity.ok(profileResponse);
}
}