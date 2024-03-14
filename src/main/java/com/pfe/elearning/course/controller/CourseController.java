package com.pfe.elearning.course.controller;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.course.dto.CourseRequest;
import com.pfe.elearning.course.dto.CourseResponse;
import com.pfe.elearning.course.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<Integer> createCourse(@Valid @RequestBody CourseRequest courseRequest,
                                                @AuthenticationPrincipal UserDetails userDetails) {
        // Get the publisher's username from the authenticated user's details
        String publisherUsername = userDetails.getUsername();

        // Create the course with the provided request and publisher's username
        Integer courseId = courseService.createCourse(courseRequest, publisherUsername);

        // Return the ID of the newly created course
        return ResponseEntity.ok(courseId);
    }
    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Integer courseId) {
        CourseResponse courseResponse = courseService.getCourseById(courseId);
        return ResponseEntity.ok(courseResponse);
    }


    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        List<CourseResponse> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{courseId}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<CourseResponse> updateCourse(
            @PathVariable Integer courseId,
            @Valid @RequestBody CourseRequest courseRequest) {
        CourseResponse updatedCourse = courseService.updateCourse(courseId, courseRequest);
        return ResponseEntity.ok(updatedCourse);
    }
    @DeleteMapping("/{courseId}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<String> deleteCourse(@PathVariable Integer courseId) {
        if (courseService.existsById(courseId)) {
            courseService.deleteCourse(courseId);
            return ResponseEntity.ok("Course " + courseId + " successfully deleted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/search")
    public ResponseEntity<PageResponse<CourseResponse>> findCoursesByTitleContaining(
            @RequestParam String keyword,
            @RequestParam int page,
            @RequestParam int size) {
        PageResponse<CourseResponse> result = courseService.findCourseByTitleContaining(keyword, page, size);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/byTopic/{topicId}")
    public ResponseEntity<PageResponse<CourseResponse>> getCoursesByTopic(
            @PathVariable Integer topicId,
            @RequestParam int page,
            @RequestParam int size) {
        PageResponse<CourseResponse> result = courseService.getCoursesByTopic(topicId, page, size);
        return ResponseEntity.ok(result);
    }
}
