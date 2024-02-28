package com.pfe.elearning.course.service.serviceImpl;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.course.dto.request.CourseRequest;
import com.pfe.elearning.course.dto.response.CourseResponse;
import com.pfe.elearning.course.entity.Course;
import com.pfe.elearning.course.repository.CourseRepository;
import com.pfe.elearning.course.dto.mapper.CourseMapper;
import com.pfe.elearning.course.service.CourseService;
import com.pfe.elearning.topic.entity.Topic;
import com.pfe.elearning.topic.repository.TopicRepository;
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
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final TopicRepository topicRepository;
    private final CourseMapper courseMapper;

    @Override
    public CourseResponse createCourse(CourseRequest courseRequest) {
        Topic topic = topicRepository.findById(courseRequest.getDomainId())
                .orElseThrow(() -> new EntityNotFoundException("Topic not found"));

        Course course = courseMapper.toCourse(courseRequest);
        course.setTopic(topic);

        Course savedCourse = courseRepository.save(course);
        return courseMapper.toCourseResponse(savedCourse);
    }
    @Override
    public CourseResponse getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
        return courseMapper.toCourseResponse(course);
    }

    @Override
    @Transactional
    public CourseResponse updateCourse(Long courseId, CourseRequest courseRequest) {
        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseId));

        // Update existingCourse with data from courseRequest
        existingCourse.setTitle(courseRequest.getTitle());
        existingCourse.setDescription(courseRequest.getDescription());

        // Get the domain associated with the provided domainId
        Topic topic = topicRepository.findById(courseRequest.getDomainId())
                .orElseThrow(() -> new EntityNotFoundException("Domain not found"));

        // Set the domain for the existing course
        existingCourse.setTopic(topic);

        // Save the updated course
        Course updatedCourse = courseRepository.save(existingCourse);

        // Map the updated course to response
        return courseMapper.toCourseResponse(updatedCourse);
    }

    @Override
    public void deleteCourse(Long courseId) {
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
    public boolean existsById(Long id) {
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
}
