package com.pfe.elearning.pdf;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfe.elearning.common.BaseEntity;
import com.pfe.elearning.lesson.entity.Lesson;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "pdfs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PDF extends BaseEntity {
    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(nullable = false)
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    @JsonIgnore
    private Lesson lesson;
}