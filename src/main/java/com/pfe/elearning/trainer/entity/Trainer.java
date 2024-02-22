package com.pfe.elearning.trainer.entity;

import com.pfe.elearning.user.entity.User;

import jakarta.persistence.DiscriminatorValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter

@NoArgsConstructor
@SuperBuilder

@DiscriminatorValue("TRAINER")

public class Trainer extends User {
    private int experience;
    private String bio;
    private String designation;


}
