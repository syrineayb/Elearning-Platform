package com.pfe.elearning.profile.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@AllArgsConstructor
@Builder
public class ProfileResponse {

    private Long profileId;
    private String firstName;
    private String lastName;
    private String email;
    private String image; // Add image field
    private String description; // Add description field
    private String phoneNumber; // Add phoneNumber field
    private Date dateOfBirth; // Add dateOfBirth field
    private String country; // Add country field
    private String currentJob; // Add currentJob field
    private int experience; // Add experience field
    private String degreeOfEducation; // Add degreeOfEducation field
    private String certificates; // Add certificates field
}