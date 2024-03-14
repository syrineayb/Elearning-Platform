package com.pfe.elearning.lesson.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class LessonRequest {
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotBlank(message = "Description cannot be blank")
    private String description;
    private Integer courseId;
}
