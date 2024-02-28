package com.pfe.elearning.course.service;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.course.dto.request.CourseRequest;
import com.pfe.elearning.course.dto.response.CourseResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {
    CourseResponse createCourse(CourseRequest courseRequest);
    CourseResponse getCourseById(Long courseId);
    CourseResponse updateCourse(Long courseId, CourseRequest courseRequest);
    void deleteCourse(Long courseId);
    List<CourseResponse> getAllCourses();
    boolean existsById(Long id);
    PageResponse<CourseResponse> findCourseByTitleContaining(String keyword, int page, int size);



}
