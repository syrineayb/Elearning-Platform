package com.pfe.elearning.candidate.entity;

import com.pfe.elearning.profile.entity.Profile;
import com.pfe.elearning.user.entity.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.experimental.SuperBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("Candidate")
@Table(name = "candidates")
public class Candidate extends User {
    //private int age;


}