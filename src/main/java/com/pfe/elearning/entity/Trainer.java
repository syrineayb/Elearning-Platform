package com.pfe.elearning.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter

@NoArgsConstructor
@SuperBuilder


public class Trainer extends User {
    private int Experience;
    private String Bio;
    private String designation;


}
