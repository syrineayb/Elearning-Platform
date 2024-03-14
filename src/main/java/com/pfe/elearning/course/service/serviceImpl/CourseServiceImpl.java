package com.pfe.elearning.course.service.serviceImpl;

import com.pfe.elearning.authentification.dto.RegisterRequest;
import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.course.dto.CourseRequest;
import com.pfe.elearning.course.dto.CourseResponse;
import com.pfe.elearning.course.entity.Course;
import com.pfe.elearning.course.repository.CourseRepository;
import com.pfe.elearning.course.dto.CourseMapper;
import com.pfe.elearning.course.service.CourseService;
import com.pfe.elearning.topic.entity.Topic;
import com.pfe.elearning.topic.repository.TopicRepository;
import com.pfe.elearning.user.entity.User;
import com.pfe.elearning.user.service.UserService;
import com.pfe.elearning.validator.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final TopicRepository topicRepository;
    private final CourseMapper courseMapper;
    private final UserService userService;
    private final ObjectsValidator<CourseRequest> validator;

    @Override
    public Integer createCourse(CourseRequest courseRequest, String publisherUsername) {
        validator.validate(courseRequest);
        User publisher = userService.getUserByUsername(publisherUsername);
        Course course = courseMapper.toCourse(courseRequest);
        course.setPublisher(publisher);
        Course savedCourse = courseRepository.save(course);

        // Return the ID of the newly created course
        return savedCourse.getId();
    }

    @Override
    public CourseResponse getCourseById(Integer courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
        return courseMapper.toCourseResponse(course);
    }

    @Override
    public CourseResponse updateCourse(Integer courseId, CourseRequest courseRequest) {
        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseId));

        // Update existingCourse with data from courseRequest
        existingCourse.setTitle(courseRequest.getTitle());
        existingCourse.setDescription(courseRequest.getDescription());
        existingCourse.setDuration(courseRequest.getDuration());
        existingCourse.setPhoto(courseRequest.getPhoto());

        // Get the topic associated with the provided topicId
        Topic topic = topicRepository.findById(courseRequest.getTopicId())
                .orElseThrow(() -> new EntityNotFoundException("Topic not found"));

        // Set the topic for the existing course
        existingCourse.setTopic(topic);

        // Save the updated course
        Course updatedCourse = courseRepository.save(existingCourse);

        // Map the updated course to response
        return courseMapper.toCourseResponse(updatedCourse);
    }

    @Override
    public void deleteCourse(Integer courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseId));
        courseRepository.delete(course);
    }

    @Override
    public List<CourseResponse> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(courseMapper::toCourseResponse)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Integer id) {
        return courseRepository.existsById(id);
    }

    @Override
    public PageResponse<CourseResponse> findCourseByTitleContaining(String keyword, int page, int size) {
        Page<Course> coursePage = courseRepository.findByTitleContaining(keyword, PageRequest.of(page, size));
        return PageResponse.<CourseResponse>builder()
                .content(
                        coursePage.getContent()
                                .stream()
                                .map(courseMapper::toCourseResponse)
                                .toList()
                )
                .totalPages(coursePage.getTotalPages())
                .build();
    }

    @Override
    public PageResponse<CourseResponse> getCoursesByTopic(Integer topicId, int page, int size) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new EntityNotFoundException("Topic not found"));

        Page<Course> coursePage = courseRepository.findByTopic(topic, PageRequest.of(page, size));

        return PageResponse.<CourseResponse>builder()
                .content(
                        coursePage.getContent()
                                .stream()
                                .map(courseMapper::toCourseResponse)
                                .toList()
                )
                .totalPages(coursePage.getTotalPages())
                .build();
    }
}
