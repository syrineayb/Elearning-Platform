package com.pfe.elearning.video;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfe.elearning.common.BaseEntity;
import com.pfe.elearning.lesson.entity.Lesson;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "videos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video extends BaseEntity {
    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    @JsonIgnore
    private Lesson lesson;
}