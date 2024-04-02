package com.pfe.elearning.profile.dto;

import com.pfe.elearning.profile.entity.Profile;
import com.pfe.elearning.user.entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class ProfileMapper {

    public ProfileResponse toProfileResponse(Profile profile) {
        User user = profile.getUser();
        LocalDateTime createdAt = user.getCreatedAt(); // Assuming getCreatedAt() returns LocalDateTime
        Date creationDate = convertToDate(createdAt);

        return ProfileResponse.builder()
                .profileId(profile.getId())
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .email(profile.getEmail())
                .profileImage((profile.getProfileImage()))
                .description(profile.getDescription())
                .phoneNumber(profile.getPhoneNumber())
                .dateOfBirth(profile.getDateOfBirth())
                .country(profile.getCountry())
                .currentJob(profile.getCurrentJob())
                .experience(profile.getExperience())
                .degreeOfEducation(profile.getDegreeOfEducation())
                .certificates(profile.getCertificates())
                .githubUrl(profile.getGithubUrl())
                .twitterUrl(profile.getTwitterUrl())
                .instagramUrl(profile.getInstagramUrl())
                .linkedInUrl(profile.getLinkedInUrl())
                .creationDate(creationDate) // Using creationDate from the User entity
                .build();
    }
    private Date convertToDate(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}