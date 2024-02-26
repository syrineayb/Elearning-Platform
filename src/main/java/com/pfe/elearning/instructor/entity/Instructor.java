package com.pfe.elearning.instructor.entity;

import com.pfe.elearning.profile.entity.Profile;
import com.pfe.elearning.user.entity.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("Instructor")
@Table(name = "instructors")
public class Instructor extends User {
    
}
