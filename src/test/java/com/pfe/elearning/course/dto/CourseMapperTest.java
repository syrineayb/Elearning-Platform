package com.pfe.elearning.course.dto;

import com.pfe.elearning.course.entity.Course;
import com.pfe.elearning.lesson.dto.LessonResponse;
import com.pfe.elearning.lesson.entity.Lesson;
import com.pfe.elearning.topic.dto.TopicMapper;
import com.pfe.elearning.topic.dto.TopicResponse;
import com.pfe.elearning.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseMapperTest {
    private TopicMapper topicMapper;
    private CourseMapper courseMapper;

    @BeforeEach
    void setUp() {
        topicMapper = mock(TopicMapper.class);
        courseMapper = new CourseMapper(topicMapper);
    }

    @Test
    void shouldMapCourseRequestToCourse() {
        // Create a CourseRequest object
        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setTitle("Test Course");
        courseRequest.setDescription("This is a test course");
        courseRequest.setImageUrl("test.jpg");
        courseRequest.setDuration("2 weeks");
        courseRequest.setTopicId(1);

        // Call the toCourse method
        Course course = courseMapper.toCourse(courseRequest);

        // Assertions
        assertNotNull(course);
        assertEquals(courseRequest.getTitle(), course.getTitle());
        assertEquals(courseRequest.getDescription(), course.getDescription());
        assertEquals(courseRequest.getImageUrl(), course.getImageUrl());
        assertEquals(courseRequest.getDuration(), course.getDuration());
        assertEquals(courseRequest.getTopicId(), course.getTopic().getId());
    }

    @Test
    void shouldMapCourseToCourseResponse() {
        // Create a Course object
        Course course = new Course();
        course.setId(1);
        course.setTitle("Test Course");
        course.setDescription("This is a test course");
        course.setCreatedAt(LocalDateTime.now());
        course.setDuration("2 weeks");
        course.setImageUrl("test.jpg");
        User publisher = new User();
        publisher.setFirstname("John");
        publisher.setLastname("Doe");
        course.setPublisher(publisher);

        // Create a simulated TopicResponse object
        TopicResponse topicResponse = new TopicResponse();
        topicResponse.setId(1);
        topicResponse.setTitle("Test Topic");

        // Configure the mock for the toTopicResponse method
        when(topicMapper.toTopicResponse(course.getTopic())).thenReturn(topicResponse);
        // Create lessons list and add a lesson
        List<Lesson> lessons = new ArrayList<>();
        Lesson lesson1 = new Lesson();
        lesson1.setId(1);
        lesson1.setTitle("Lesson 1");
        lesson1.setDescription("This is lesson 1");
        lesson1.setCreatedAt(LocalDateTime.now());
        lesson1.setCourse(course);
        lessons.add(lesson1);
        course.setLessons(lessons);
        // Call the toCourseResponse method
        CourseResponse courseResponse = courseMapper.toCourseResponse(course);

        // Assertions
        assertNotNull(courseResponse);
        assertEquals(course.getId(), courseResponse.getId());
        assertEquals(course.getTitle(), courseResponse.getTitle());
        assertEquals(course.getDescription(), courseResponse.getDescription());
        assertEquals(course.getCreatedAt(), courseResponse.getCreatedAt());
        assertEquals(course.getDuration(), courseResponse.getDuration());
        assertEquals(course.getImageUrl(), courseResponse.getImageUrl());
        assertEquals(topicResponse.getId(), courseResponse.getTopic().getId());
        assertEquals(topicResponse.getTitle(), courseResponse.getTopic().getTitle());
        assertEquals(lessons.size(), courseResponse.getLessons().size());
    }

    @Test
    void shouldReturnEmptyListOfLessonResponsesWhenCourseHasNullListOfLessons() {
        // Test case for handling null list of lessons
        // Create a Course object with null list of lessons
        Course course = new Course();
        course.setId(1);
        course.setTitle("Test Course");
        course.setDescription("This is a test course");
        course.setCreatedAt(LocalDateTime.now());
        course.setDuration("2 weeks");
        course.setImageUrl("test.jpg");
        User publisher = new User();
        publisher.setFirstname("John");
        publisher.setLastname("Doe");
        course.setPublisher(publisher);
        course.setLessons(null); // Set the list of lessons to null

        // Call the method to map lesson responses
        List<LessonResponse> lessonResponses = courseMapper.mapLessonResponses(course.getLessons());

        // Assertions
        assertNotNull(lessonResponses);
        assertTrue(lessonResponses.isEmpty()); // Ensure that an empty list of lesson responses is returned
    }
    @Test
    void shouldThrowNullPointerExceptionWhenCourseRequestIsNull() {
        // Act & Assert
        NullPointerException exception = assertThrows(NullPointerException.class, () -> courseMapper.toCourse(null));
        assertEquals("The course request should not be null", exception.getMessage());
    }



}
