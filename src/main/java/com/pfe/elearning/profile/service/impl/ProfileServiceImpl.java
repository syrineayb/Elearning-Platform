package com.pfe.elearning.profile.service.impl;

import com.pfe.elearning.authentification.dto.response.AuthResponse;
import com.pfe.elearning.authentification.service.JwtService;
import com.pfe.elearning.profile.dto.ProfileMapper;
import com.pfe.elearning.profile.dto.ProfileRequest;
import com.pfe.elearning.profile.dto.ProfileResponse;
import com.pfe.elearning.profile.entity.Profile;
import com.pfe.elearning.profile.repository.ProfileRepository;
import com.pfe.elearning.profile.service.ProfileService;
import com.pfe.elearning.token.repository.TokenRepository;
import com.pfe.elearning.user.entity.User;
import com.pfe.elearning.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    // private final PasswordEncoder passwordEncoder;

   /* @Autowired
    public CandidateProfileServiceImpl(profileR candidateRepository, PasswordEncoder passwordEncoder) {
        this.candidateRepository = candidateRepository;
        this.passwordEncoder = passwordEncoder;
    }
    */

    @Override
    public ProfileResponse getCurrentUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return profileMapper.toProfileResponse(user.getProfile());
    }

    @Override
    public ProfileResponse updateProfile( ProfileRequest profileRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        Profile profile = user.getProfile();
        updateProfileFields(profile, profileRequest); // Passer le paramètre 'file' ici
        Profile updatedProfile = profileRepository.save(profile);

        user.setFirstname(profile.getFirstName());
        user.setLastname(profile.getLastName());
        user.setEmail(profileRequest.getEmail());
        userRepository.save(user);

        String newToken = jwtService.generateToken(user);

        return ProfileResponse.fromProfile(updatedProfile, newToken);
    }
    private void updateProfileFields(Profile profile, ProfileRequest profileRequest) {
        // Mettre à jour chaque champ du profil avec les valeurs fournies dans le ProfileRequest
        profile.setFirstName(profileRequest.getFirstName());
        profile.setLastName(profileRequest.getLastName());
        profile.setEmail(profileRequest.getEmail());
        profile.setDescription(profileRequest.getDescription());
        profile.setCountry(profileRequest.getCountry());
        profile.setCurrentJob(profileRequest.getCurrentJob());
        profile.setExperience(profileRequest.getExperience());
        profile.setCertificates(profileRequest.getCertificates());
        profile.setDegreeOfEducation(profileRequest.getDegreeOfEducation());
        profile.setPhoneNumber(profileRequest.getPhoneNumber());

        profile.setGithubUrl(profileRequest.getGithubUrl());
        profile.setTwitterUrl(profileRequest.getTwitterUrl());
        profile.setInstagramUrl(profileRequest.getInstagramUrl());
        profile.setLinkedInUrl(profileRequest.getLinkedInUrl());

        // Vérifier si une nouvelle image de profil est fournie



    }
    @Override
    public AuthResponse updateUserEmail(String oldEmail, String newEmail) {
        // Find the user by the old email
        User user = userRepository.findByEmail(oldEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + oldEmail));

        // Update the user's email address
        user.setEmail(newEmail);
        userRepository.save(user);

        // Find and invalidate existing tokens associated with the old email address
        revokeAllUserTokens(user);

        // Update the email in the user's profile (if applicable)
        Profile profile = profileRepository.findByEmail(oldEmail);
        if (profile != null) {
            profile.setEmail(newEmail);
            profileRepository.save(profile);
        }
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRoles());
        claims.put("active", user.isActive());

        String newToken = jwtService.generateToken(user, claims);

        // Generate a new token for the updated email
       // String newToken = jwtService.generateToken(user);

        // Return the updated user information and new token in the response
        return AuthResponse.builder()
                .username(user.getUsername())
                .userId(user.getId())
                .accessToken(newToken)
                .refreshToken(jwtService.generateRefreshToken(user))
                .createdAt(user.getCreatedAt())
                .build();
    }

    @Override
    public Profile getById(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found for ID: " + id));
    }


    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (!validUserTokens.isEmpty()) {
            validUserTokens.forEach(token -> {
                token.setExpired(true);
                token.setRevoked(true);
            });
            tokenRepository.saveAll(validUserTokens);
        }
    }
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
        //existingProfile.setProfileImage(profileRequest.getProfileImage());
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