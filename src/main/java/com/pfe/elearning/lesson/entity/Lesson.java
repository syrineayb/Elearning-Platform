package com.pfe.elearning.lesson.entity;

import com.pfe.elearning.common.BaseEntity;
import com.pfe.elearning.course.entity.Course;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
@Table(name = "lessons")
public class Lesson extends BaseEntity {
    @NotBlank
    @Column(nullable = false,unique = true)
    private String title;

    @NotBlank
    @Column(nullable = false)
    @Size(min = 2)
    private String description;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}
