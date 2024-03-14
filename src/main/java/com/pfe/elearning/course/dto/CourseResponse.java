package com.pfe.elearning.course.dto;

import com.pfe.elearning.lesson.dto.LessonResponse;
import com.pfe.elearning.topic.dto.TopicResponse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
public class CourseResponse {
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
  //  private String topicTitle;
    private String photo;
    private String duration;
    private String publisherUsername;
    private TopicResponse topic;
    private List<LessonResponse> lessons;

}
