package com.pfe.elearning.lesson.service.ServiceImpl;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.course.entity.Course;
import com.pfe.elearning.course.repository.CourseRepository;
import com.pfe.elearning.lesson.dto.LessonMapper;
import com.pfe.elearning.lesson.dto.LessonRequest;
import com.pfe.elearning.lesson.dto.LessonResponse;
import com.pfe.elearning.lesson.entity.Lesson;
import com.pfe.elearning.lesson.repository.LessonRepository;
import com.pfe.elearning.lesson.service.LessonService;
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
    private final ObjectsValidator<LessonRequest> validator;


    @Override
    public LessonResponse createLesson(LessonRequest lessonRequest) {
        validator.validate(lessonRequest);
        Course course = courseRepository.findById(lessonRequest.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        Lesson lesson = lessonMapper.toLesson(lessonRequest);
        lesson.setCourse(course);
        Lesson savedLesson = lessonRepository.save(lesson);
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
    public LessonResponse updateLesson(Long lessonId, LessonRequest lessonRequest) {
        Lesson existingLesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id: " + lessonId));

        // Update the existing lesson with the new information
        existingLesson.setTitle(lessonRequest.getTitle());
        existingLesson.setDescription(lessonRequest.getDescription());

        Course course = courseRepository.findById(lessonRequest.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found !"));

        existingLesson.setCourse(course);

        Lesson updatedLesson = lessonRepository.save(existingLesson);
        return lessonMapper.toLessonResponse(updatedLesson);
    }

    @Override
    public void deleteLesson(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id: " + lessonId));

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
