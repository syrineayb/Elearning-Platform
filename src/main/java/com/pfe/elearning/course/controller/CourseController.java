package com.pfe.elearning.course.controller;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.course.dto.request.CourseRequest;
import com.pfe.elearning.course.dto.response.CourseResponse;
import com.pfe.elearning.course.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @PreAuthorize("hasAnyRole('INSTRUCTOR')")
    public ResponseEntity<CourseResponse> createCourse(@RequestBody @Valid CourseRequest courseRequest) {
        CourseResponse createdCourse = courseService.createCourse(courseRequest);
        return ResponseEntity.ok(createdCourse);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long courseId) {
        CourseResponse course = courseService.getCourseById(courseId);
        return ResponseEntity.ok(course);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        List<CourseResponse> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{courseId}")
    @PreAuthorize("hasAnyRole('INSTRUCTOR')")
    public ResponseEntity<CourseResponse> updateCourse(
            @PathVariable Long courseId,
            @RequestBody @Valid CourseRequest courseRequest) {
        CourseResponse updatedCourse = courseService.updateCourse(courseId, courseRequest);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{courseId}")
    @PreAuthorize("hasAnyRole('INSTRUCTOR')")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {
        if (courseService.existsById(courseId)) {
            courseService.deleteCourse(courseId);
            return ResponseEntity.ok("Course " + courseId + " successfully deleted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/search")
   // @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<PageResponse<CourseResponse>> findByTitleContaining(
            @RequestParam(name = "keyword") String keyword,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "20", required = false) int size) {
        return ResponseEntity.ok(courseService.findCourseByTitleContaining(keyword, page, size));
    }
}
