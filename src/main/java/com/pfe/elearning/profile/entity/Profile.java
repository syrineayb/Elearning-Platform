package com.pfe.elearning.profile.entity;
import com.pfe.elearning.common.BaseEntity;
import com.pfe.elearning.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profiles")

public class Profile extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    //private String oldPassword;
    private String newPassword;
    private String image;
    private String description;
    private String phoneNumber;
    private Date dateOfBirth;
    private String country;
    private String currentJob;
    //private String linkedInUsername;
    //private String githubUsername;
    //private String skills;
    private int experience;//// Experience(position/company/location/from/responsabilities)
    private String degreeOfEducation;
    private String certificates;

   /* @OneToOne
    @JoinColumn(name = "user_id")database
    private User user;

    */
   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_id", nullable = false)
   private User user;
}
