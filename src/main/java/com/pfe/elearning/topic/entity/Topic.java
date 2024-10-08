package com.pfe.elearning.topic.entity;

import com.pfe.elearning.candidate.entity.Candidate;
import com.pfe.elearning.common.BaseEntity;
import com.pfe.elearning.course.entity.Course;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "topics")
public class Topic extends BaseEntity {

    @Column(unique = true)
    @NotNull(message = "Title cannot be null")
    @NotBlank(message = "Title cannot be blank")
    private String title;
    private String imageUrl;
//nb of  courses

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();

    public Topic(String title) {
        this.title = title;
    }

 /*   @ManyToMany(mappedBy = "topics")
    private List<Candidate> candidates;


  */
}
