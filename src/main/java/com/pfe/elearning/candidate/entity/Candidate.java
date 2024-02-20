package com.pfe.elearning.candidate.entity;

import com.pfe.elearning.user.entity.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.experimental.SuperBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("CANDIDATE")
public class Candidate extends User {
    private int age;
}