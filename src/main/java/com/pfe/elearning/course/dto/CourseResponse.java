package com.pfe.elearning.course.dto;

import com.pfe.elearning.lesson.dto.LessonResponse;
import com.pfe.elearning.topic.dto.TopicResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CourseResponse {
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
  //  private String topicTitle;
    private String imageUrl;
    private String duration;
    private String publisherName; // Add this field for the publisher's name
    private TopicResponse topic;
    private List<LessonResponse> lessons;

}
