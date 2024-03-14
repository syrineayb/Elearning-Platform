package com.pfe.elearning.course.service;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.course.dto.CourseRequest;
import com.pfe.elearning.course.dto.CourseResponse;

import java.util.List;

public interface CourseService {
    public Integer createCourse(CourseRequest courseRequest, String publisherUsername);
    CourseResponse getCourseById(Integer courseId);
    CourseResponse updateCourse(Integer courseId, CourseRequest courseRequest);
    void deleteCourse(Integer courseId);
    List<CourseResponse> getAllCourses();
    boolean existsById(Integer id);
    PageResponse<CourseResponse> findCourseByTitleContaining(String keyword, int page, int size);
    PageResponse<CourseResponse> getCoursesByTopic(Integer topicId, int page, int size);
}
