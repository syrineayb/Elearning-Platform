package com.pfe.elearning.lesson.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LessonResponse {
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private String CourseTitle;

}
