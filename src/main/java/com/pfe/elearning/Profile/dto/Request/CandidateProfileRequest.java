package com.pfe.elearning.Profile.dto.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateProfileRequest {
    private long oldPassword;
    private int age;
    private String firstName;
    private String lastName;
   private long newPassword;
}