package com.pfe.elearning.profile.service;

import com.pfe.elearning.profile.dto.Request.ProfileRequest;
import com.pfe.elearning.profile.dto.Response.ProfileResponse;

public interface ProfileService {
   // ProfileResponse getCandidateProfile(Long candidateId);
   ProfileResponse getUserProfile(Long userId);

    ProfileResponse updateUserProfile(Long userId, ProfileRequest profileRequest);
}