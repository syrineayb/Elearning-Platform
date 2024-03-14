package com.pfe.elearning.course.dto;

import com.pfe.elearning.course.entity.Course;
import com.pfe.elearning.topic.entity.Topic;
import org.springframework.stereotype.Service;

@Service
public class CourseMapper {
    public CourseResponse toCourseResponse(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .createdAt(course.getCreatedAt())
                .duration(course.getDuration())
                .publisherUsername(course.getPublisherUsername())
                .photo(course.getPhoto())
                //.topic(toTopicResponse(course.getTopic())) // Assuming you have a toTopicResponse method
                //.lessons(course.getLessons())
                .build();
    }

    public Course toCourse(CourseRequest courseRequest) {
        Course course = new Course();
        course.setTitle(courseRequest.getTitle());
        course.setDescription(courseRequest.getDescription());
        Topic topic = new Topic();
        topic.setId(courseRequest.getTopicId());
        course.setTopic(topic);
        return course;
    }
}