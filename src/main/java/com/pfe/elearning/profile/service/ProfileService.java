package com.pfe.elearning.profile.service;

import com.pfe.elearning.profile.dto.ProfileRequest;
import com.pfe.elearning.profile.dto.ProfileResponse;

public interface ProfileService {
   // ProfileResponse getCandidateProfile(Long candidateId);
   ProfileResponse getUserProfile(Long userId);

    ProfileResponse updateUserProfile(Long userId, ProfileRequest profileRequest);
}