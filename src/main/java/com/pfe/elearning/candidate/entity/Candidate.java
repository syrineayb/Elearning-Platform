package com.pfe.elearning.candidate.entity;

import com.pfe.elearning.topic.entity.Topic;
import com.pfe.elearning.user.entity.User;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@DiscriminatorValue("Candidate")
@Table(name = "candidates")
public class Candidate extends User {
    //private int age;
   /* @ManyToMany
    @JoinTable(
            name = "candidates_topics",
            joinColumns = {@JoinColumn(name = "candidate_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "topic_id", referencedColumnName = "id")}
    )
    // @JsonManagedReference
    private List<Topic> topics;



    */
}