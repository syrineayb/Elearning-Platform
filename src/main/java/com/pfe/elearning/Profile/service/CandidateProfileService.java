package com.pfe.elearning.Profile.service;

import com.pfe.elearning.Profile.dto.Request.CandidateProfileRequest;
import com.pfe.elearning.Profile.dto.Response.CandidateProfileResponse;

public interface CandidateProfileService {
    CandidateProfileResponse getCandidateProfile(Long candidateId);
    CandidateProfileResponse updateCandidateProfile(Long candidateId, CandidateProfileRequest profileRequest);
}