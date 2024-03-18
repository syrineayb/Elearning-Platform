package com.pfe.elearning.lesson.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfe.elearning.common.BaseEntity;
import com.pfe.elearning.course.entity.Course;
import com.pfe.elearning.pdf.PDF;
import com.pfe.elearning.user.entity.User;
import com.pfe.elearning.video.Video;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

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
  /*  @Column(name = "publisher_username")
    private String publisherUsername;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    private User publisher;

   */

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Video> videos;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PDF> pdfs;

}
