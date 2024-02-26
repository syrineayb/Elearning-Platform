package com.pfe.elearning.profile.dto.Request;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
@Getter
@Setter
public class ProfileRequest {
   @NotNull(message = "First name is mandatory")
   @NotBlank(message = "First name is mandatory")
   private String firstName;

     @NotNull(message = "Last name is mandatory")
     @NotBlank(message = "Last name is mandatory")
     private String lastName;

     // Add other fields you want to update, such as image, password, etc.


    @NotNull(message = "Last Email is mandatory")
    @NotBlank(message = "Last Email is mandatory")
    @Email
    private String email;

    private String image;
    private String description;
    private String phoneNumber;
    private Date dateOfBirth;
    private String country;
    private String currentJob;
    private int experience;
    private String degreeOfEducation;
    private String certificates;



}