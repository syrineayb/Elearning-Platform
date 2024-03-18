package com.pfe.elearning.lesson.dto;

import com.pfe.elearning.course.entity.Course;
import com.pfe.elearning.lesson.entity.Lesson;
import org.springframework.stereotype.Service;

@Service
public class LessonMapper {
    public LessonResponse toLessonResponse(Lesson lesson) {
        return LessonResponse.builder()
                .id(lesson.getId())
                .title(lesson.getTitle())
                .description(lesson.getDescription())
                .createdAt(lesson.getCreatedAt())
                .courseTitle(lesson.getCourse().getTitle())
              //  .publisherName(lesson.getPublisher().getName()) // Assuming User entity has getName() method
                .build();
    }
    public Lesson toLesson(LessonRequest lessonRequest) {
        Lesson lesson = new Lesson();
        lesson.setTitle(lessonRequest.getTitle());
        lesson.setDescription(lessonRequest.getDescription());
        Course course = new Course();
        course.setId(lessonRequest.getCourseId());
        lesson.setCourse(course);
        return lesson;
    }
}
