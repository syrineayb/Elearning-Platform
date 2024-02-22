package com.pfe.elearning.Profile.dto.Mapper;

import com.pfe.elearning.Profile.dto.Response.CandidateProfileResponse;
import com.pfe.elearning.candidate.entity.Candidate;

public class CandidateMapper {
    public static CandidateProfileResponse toCandidateProfileResponse(Candidate candidate) {
        CandidateProfileResponse response = new CandidateProfileResponse();
        response.setFirstName(candidate.getFirstname());
        response.setLastName(candidate.getLastname());
        response.setAge(candidate.getAge());
        // Mappez d'autres champs nécessaires pour la réponse du profil du candidat
        return response;
    }
}
