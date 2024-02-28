package com.pfe.elearning.candidate.entity;

import com.pfe.elearning.user.entity.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("Candidate")
@Table(name = "candidates")
public class Candidate extends User {
    //private int age;
   /* @ManyToMany
    @JoinTable(name = "candidate_domains",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "domain_id"))
    private List<Domain> domains;

    */

}