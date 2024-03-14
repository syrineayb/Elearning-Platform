package com.pfe.elearning.profile.dto;

import com.pfe.elearning.profile.entity.Profile;
import org.springframework.stereotype.Service;

@Service

public class ProfileMapper {

    public ProfileResponse toProfileResponse(Profile profile) {
        return ProfileResponse.builder()
                .profileId(profile.getId())
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .email(profile.getEmail())
                .image(profile.getImage())
                .description(profile.getDescription())
                .phoneNumber(profile.getPhoneNumber())
                .dateOfBirth(profile.getDateOfBirth())
                .country(profile.getCountry())
                .currentJob(profile.getCurrentJob())
                .experience(profile.getExperience())
                .degreeOfEducation(profile.getDegreeOfEducation())
                .certificates(profile.getCertificates())
                .phoneNumber(profile.getPhoneNumber())
                .build();
    }
}
