package com.pfe.elearning.instructor.entity;

import com.pfe.elearning.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("INSTRUCTOR")
@Table(name = "instructors")
public class Instructor extends User {

   /* @ManyToMany
    @JoinTable(name = "instructor_domains",
            joinColumns = @JoinColumn(name = "instructor_id"),
            inverseJoinColumns = @JoinColumn(name = "domain_id"))
    private List<Domain> domains;

    */
    
}
