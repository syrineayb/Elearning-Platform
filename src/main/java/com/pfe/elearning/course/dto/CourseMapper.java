package com.pfe.elearning.course.dto;

import com.pfe.elearning.course.entity.Course;
import com.pfe.elearning.lesson.dto.LessonResponse;
import com.pfe.elearning.lesson.entity.Lesson;
import com.pfe.elearning.topic.dto.TopicMapper;
import com.pfe.elearning.topic.entity.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseMapper {
    private final TopicMapper topicMapper;


    public CourseResponse toCourseResponse(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .createdAt(course.getCreatedAt())
                .duration(course.getDuration())
               // .publisherUsername(course.getPublisherUsername())
                .publisherName(course.getPublisher().getName()) // Assuming User entity has getName() method
                .photo(course.getPhoto())
                .topic(topicMapper.toTopicResponse(course.getTopic()))
                .lessons(mapLessonResponses(course.getLessons()))

                .build();
    }

    public Course toCourse(CourseRequest courseRequest) {
        if(courseRequest == null){
            throw new NullPointerException("The course request should not be null");
        }
        Course course = new Course();
        course.setTitle(courseRequest.getTitle());
        course.setDescription(courseRequest.getDescription());
        course.setPhoto(courseRequest.getPhoto());
        course.setDuration(courseRequest.getDuration());
        Topic topic = new Topic();
        topic.setId(courseRequest.getTopicId());
        course.setTopic(topic);
        return course;
    }

    List<LessonResponse> mapLessonResponses(List<Lesson> lessons) {
        if (lessons == null) {
            return Collections.emptyList(); // Return an empty list if lessons is null
        }
        return lessons.stream()
                .map(lesson -> LessonResponse.builder()
                        .id(lesson.getId())
                        .title(lesson.getTitle())
                        .description(lesson.getDescription())
                        .createdAt(lesson.getCreatedAt())
                        .courseTitle(lesson.getCourse().getTitle()) // Assuming Lesson has a reference to Course
                       // .publisherName(lesson.getPublisher().getName())
                        .build())
                .collect(Collectors.toList());
    }
}