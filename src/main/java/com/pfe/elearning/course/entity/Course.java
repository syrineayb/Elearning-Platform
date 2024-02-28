package com.pfe.elearning.course.entity;

import com.pfe.elearning.common.BaseEntity;
import com.pfe.elearning.topic.entity.Topic;
import com.pfe.elearning.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "courses")
public class Course extends BaseEntity {
    @NotBlank
    @Column(nullable = false)
    @Size(min = 2,max =100)
    private String title;

    @NotBlank
    @Column(nullable = false)
    @Size(min = 2,max =100)
    private String description;

    @ManyToOne
    @JoinColumn(name = "topic_id") // Assuming domain_id is the foreign key column in the courses table
    private Topic topic; // Define many-to-one association with Domain

    @ManyToMany(mappedBy = "enrolledCourses")
    private List<User> enrolledUsers;


    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private User instructor;
}
