package com.pfe.elearning.profile.service.impl;

import com.pfe.elearning.profile.dto.Mapper.ProfileMapper;
import com.pfe.elearning.profile.dto.Request.ProfileRequest;
import com.pfe.elearning.profile.dto.Response.ProfileResponse;
import com.pfe.elearning.profile.entity.Profile;
import com.pfe.elearning.profile.repository.ProfileRepository;
import com.pfe.elearning.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    // private final PasswordEncoder passwordEncoder;

   /* @Autowired
    public CandidateProfileServiceImpl(profileR candidateRepository, PasswordEncoder passwordEncoder) {
        this.candidateRepository = candidateRepository;
        this.passwordEncoder = passwordEncoder;
    }
    */



    @Override
    public ProfileResponse getUserProfile(Long userId) {
        Profile profile = profileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for user with ID: " + userId));

        // Map the profile entity to a DTO using the ProfileMapper
        return profileMapper.toProfileResponse(profile);
    }

    @Override
    public ProfileResponse updateUserProfile(Long userId, ProfileRequest profileRequest) {
        // Retrieve the existing profile entity from the repository based on the user ID
        Profile existingProfile = profileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for user with ID: " + userId));

        // Update the profile entity with information from the profileRequest
        existingProfile.setFirstName(profileRequest.getFirstName());
        existingProfile.setLastName(profileRequest.getLastName());
        existingProfile.setEmail(profileRequest.getEmail());
        // Update other fields as needed

        // Save the updated profile entity back to the repository
        Profile updatedProfile = profileRepository.save(existingProfile);

        // Map the updated profile entity to a ProfileResponse using the ProfileMapper
        return profileMapper.toProfileResponse(updatedProfile);
    }

 /*   @Override
    public ProfileResponse updateCandidateProfile(Long candidateId, ProfileRequest profileRequest) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new IllegalArgumentException("Candidate not found with id: " + candidateId));

        // Check if the old password matches the current password
        if (passwordEncoder.matches(profileRequest.getOldPassword(), candidate.getPassword())) {
            // Update the candidate's profile with the new password
            candidate.setPassword(passwordEncoder.encode(profileRequest.getNewPassword()));
            candidate.setFirstname(profileRequest.getFirstName());
            candidate.setLastname(profileRequest.getLastName());
            candidate.setEmail(profileRequest.getEmail());
        //    candidate.setImgUrl(profileRequest.getImage());
            // Update other profile information as needed
            candidateRepository.save(candidate);
            return CandidateMapper.toCandidateProfileResponse(candidate);
        } else {
            // If old password doesn't match, throw an exception or handle it accordingly
            throw new IllegalArgumentException("Old password is incorrect");
        }
    }

  */

}
