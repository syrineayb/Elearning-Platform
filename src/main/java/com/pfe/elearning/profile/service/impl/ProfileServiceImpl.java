package com.pfe.elearning.profile.service.impl;

import com.pfe.elearning.profile.dto.ProfileMapper;
import com.pfe.elearning.profile.dto.ProfileRequest;
import com.pfe.elearning.profile.dto.ProfileResponse;
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
        existingProfile.setImage(profileRequest.getImage());
        existingProfile.setDescription(profileRequest.getDescription());
        existingProfile.setCountry(profileRequest.getCountry());
        existingProfile.setCurrentJob(profileRequest.getCurrentJob());
        existingProfile.setExperience(profileRequest.getExperience());
        existingProfile.setDateOfBirth(profileRequest.getDateOfBirth());
        existingProfile.setCertificates(profileRequest.getCertificates());
        existingProfile.setDegreeOfEducation(profileRequest.getDegreeOfEducation());
        existingProfile.setPhoneNumber(profileRequest.getPhoneNumber());
        // Update other fields as needed

        // Save the updated profile entity back to the repository
        Profile updatedProfile = profileRepository.save(existingProfile);

        // Map the updated profile entity to a ProfileResponse using the ProfileMapper
        return profileMapper.toProfileResponse(updatedProfile);
    }

}