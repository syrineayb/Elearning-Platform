package com.pfe.elearning.lesson.service.ServiceImpl;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.course.entity.Course;
import com.pfe.elearning.course.repository.CourseRepository;
import com.pfe.elearning.exception.UnauthorizedAccessException;
import com.pfe.elearning.lesson.dto.LessonMapper;
import com.pfe.elearning.lesson.dto.LessonRequest;
import com.pfe.elearning.lesson.dto.LessonResponse;
import com.pfe.elearning.lesson.entity.Lesson;
import com.pfe.elearning.lesson.repository.LessonRepository;
import com.pfe.elearning.lesson.service.LessonService;
import com.pfe.elearning.user.entity.User;
import com.pfe.elearning.user.service.UserService;
import com.pfe.elearning.validator.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;
    private final CourseRepository courseRepository;
    private final UserService userService;
    private final ObjectsValidator<LessonRequest> validator;


    @Override
    public LessonResponse createLesson(LessonRequest lessonRequest, String publisherUsername) {
        // Validate the lesson request
        validator.validate(lessonRequest);

        // Retrieve the user (publisher) by email
        User publisher = userService.getUserByEmail(publisherUsername);

        // Retrieve the course associated with the lesson
        Course course = courseRepository.findById(lessonRequest.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        // Check if the authenticated user is the publisher of the course
        if (!course.getPublisher().getEmail().equals(publisherUsername)) {
            throw new UnauthorizedAccessException("You are not authorized to add lessons for this course.");
        }

        // If authorized, proceed with creating the lesson
        Lesson lesson = lessonMapper.toLesson(lessonRequest);
        lesson.setCourse(course);
       // lesson.setPublisher(publisher);
        Lesson savedLesson = lessonRepository.save(lesson);

        // Map the saved lesson to a response and return
        return lessonMapper.toLessonResponse(savedLesson);
    }

    @Override
    public LessonResponse getLessonById(Long lessonId) {
        return lessonRepository.findById(lessonId)
                .map(lessonMapper::toLessonResponse)
                .orElse(null);
    }

    @Override
    @Transactional
    public LessonResponse updateLesson(Long lessonId, LessonRequest lessonRequest, String publisherUsername) {
        Lesson existingLesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found with id: " + lessonId));

        // Retrieve the associated course and its publisher
        Course course = existingLesson.getCourse();
        String coursePublisherUsername = course.getPublisher().getEmail();

        // Check if the authenticated user is the publisher of the course
        if (!coursePublisherUsername.equals(publisherUsername)) {
            throw new UnauthorizedAccessException("You are not authorized to update this lesson.");
        }

        // If authorized, proceed with updating the lesson
        // Update the existing lesson with the new information
        existingLesson.setTitle(lessonRequest.getTitle());
        existingLesson.setDescription(lessonRequest.getDescription());
        existingLesson.setCourse(course);

        Lesson updatedLesson = lessonRepository.save(existingLesson);
        return lessonMapper.toLessonResponse(updatedLesson);
    }

    @Override
    public void deleteLesson(Long lessonId, String publisherUsername) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found with id: " + lessonId));

        // Retrieve the associated course and its publisher
        Course course = lesson.getCourse();
        String coursePublisherUsername = course.getPublisher().getEmail();

        // Check if the authenticated user is the publisher of the course
        if (!coursePublisherUsername.equals(publisherUsername)) {
            throw new UnauthorizedAccessException("You are not authorized to delete this lesson.");
        }

        // If authorized, proceed with deleting the lesson
        lessonRepository.delete(lesson);
    }

    @Override
    public PageResponse<LessonResponse> getAllLessons(int page, int size) {
        Page<Lesson> lessonPage = lessonRepository.findAll(PageRequest.of(page, size));
        return new PageResponse<>(
                lessonPage.getContent().stream()
                        .map(lessonMapper::toLessonResponse)
                        .toList(),
                lessonPage.getTotalPages(),
                lessonPage.getTotalElements()
        );
    }

    @Override
    public PageResponse<LessonResponse> findLessonByTitleContaining(String keyword, int page, int size) {
        Page<Lesson> lessonPage = lessonRepository.findByTitleContaining(keyword, PageRequest.of(page, size));
        return new PageResponse<>(
                lessonPage.getContent().stream()
                        .map(lessonMapper::toLessonResponse)
                        .toList(),
                lessonPage.getTotalPages(),
                lessonPage.getTotalElements()
        );
    }
}
