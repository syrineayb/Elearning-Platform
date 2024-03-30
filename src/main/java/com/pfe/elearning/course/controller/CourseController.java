package com.pfe.elearning.course.controller;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.course.dto.CourseRequest;
import com.pfe.elearning.course.dto.CourseResponse;
import com.pfe.elearning.course.service.CourseService;
import com.pfe.elearning.topic.dto.TopicResponse;
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
    public ResponseEntity<String> createCourse(@Valid @RequestBody CourseRequest courseRequest,
                                                @AuthenticationPrincipal UserDetails userDetails) {

        // Get the publisher's username from the authenticated user's details
        String publisherUsername = userDetails.getUsername();

        // Create the course with the provided request and publisher's username
        courseService.createCourse(courseRequest, publisherUsername);

        // Return the ID of the newly created course
         return ResponseEntity.ok("Course created successfully");
    }
    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Integer courseId) {
        CourseResponse courseResponse = courseService.getCourseById(courseId);
        return ResponseEntity.ok(courseResponse);
    }


    @GetMapping("/findAll")
    public ResponseEntity<PageResponse<CourseResponse>> findAll(
            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
            @RequestParam(name = "size", defaultValue = "4", required = false) int size) {
        return ResponseEntity.ok(courseService.findAll(page, size));
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        List<CourseResponse> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{courseId}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<String> updateCourse(
            @PathVariable Integer courseId,
            @Valid @RequestBody CourseRequest courseRequest) {
        courseService.updateCourse(courseId, courseRequest);
        return ResponseEntity.ok("Course " + courseId + " successfully updated.");
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
