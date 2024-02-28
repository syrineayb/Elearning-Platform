package com.pfe.elearning.course.dto.mapper;

import com.pfe.elearning.course.dto.request.CourseRequest;
import com.pfe.elearning.course.dto.response.CourseResponse;
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
                .topicTitle(course.getTopic().getTitle())
                .build();
    }

    public Course toCourse(CourseRequest courseRequest) {
        Course course = new Course();
        course.setTitle(courseRequest.getTitle());
        course.setDescription(courseRequest.getDescription());
        Topic topic = new Topic();
        topic.setId(courseRequest.getDomainId());
        course.setTopic(topic);
        return course;
    }
}
