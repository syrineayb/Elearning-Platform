package com.pfe.elearning.course.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfe.elearning.common.BaseEntity;
import com.pfe.elearning.lesson.entity.Lesson;
import com.pfe.elearning.topic.entity.Topic;
import com.pfe.elearning.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Builder
@Entity
@Table(name = "courses")
public class Course extends BaseEntity {

    @NotBlank(message = "Title cannot be blank")
    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    @Column(nullable = false)
    @Size(min = 2, message = "Description must be at least 2 characters")
    private String description;

    private String photo;
    private String duration;

    @Column(name = "publisher_username")
    private String publisherUsername;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    private User publisher;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons = new ArrayList<>();
}