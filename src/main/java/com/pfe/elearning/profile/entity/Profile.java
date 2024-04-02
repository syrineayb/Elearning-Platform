package com.pfe.elearning.profile.entity;
import com.pfe.elearning.common.BaseEntity;
import com.pfe.elearning.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
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
    private String profileImage; // This field will store the URL of the image
    private String description;
    private String phoneNumber;
    private Date dateOfBirth;
    private String country;
    private String currentJob;

    private int experience;//// Experience(position/company/location/from/responsabilities)
    private String degreeOfEducation;
    private String certificates;
    private String linkedInUrl;
    private String githubUrl;
    private String twitterUrl;
    private String instagramUrl;




    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}