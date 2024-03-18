package com.pfe.elearning.lesson.controller;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.lesson.dto.LessonRequest;
import com.pfe.elearning.lesson.dto.LessonResponse;
import com.pfe.elearning.lesson.service.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;


    @PostMapping
    @PreAuthorize("hasAnyRole('INSTRUCTOR')")
    public ResponseEntity<LessonResponse> createLesson(@Valid @RequestBody LessonRequest lessonRequest,
                                                       @AuthenticationPrincipal UserDetails userDetails) {
        String publisherUsername = userDetails.getUsername();

        LessonResponse createdLesson = lessonService.createLesson(lessonRequest, publisherUsername);
        return ResponseEntity.ok(createdLesson);
    }

    @GetMapping("/{lessonId}")
    @PreAuthorize("hasAnyRole('INSTRUCTOR')")
    public ResponseEntity<LessonResponse> getLessonById(@PathVariable Long lessonId) {
        LessonResponse lessonResponse = lessonService.getLessonById(lessonId);
        return ResponseEntity.ok(lessonResponse);
    }

    @PutMapping("/{lessonId}")
    @PreAuthorize("hasAnyRole('INSTRUCTOR')")
    public ResponseEntity<LessonResponse> updateLesson(
            @PathVariable Long lessonId,
            @Valid @RequestBody LessonRequest lessonRequest,
             @AuthenticationPrincipal UserDetails userDetails){
        String publisherUsername = userDetails.getUsername();
        LessonResponse updatedLesson = lessonService.updateLesson(lessonId, lessonRequest,publisherUsername);
        return ResponseEntity.ok(updatedLesson);
    }

    @DeleteMapping("/{lessonId}")
    @PreAuthorize("hasAnyRole('INSTRUCTOR')")
    public ResponseEntity<String> deleteLesson(@PathVariable Long lessonId,
             @AuthenticationPrincipal UserDetails userDetails){
        String publisherUsername = userDetails.getUsername();
        lessonService.deleteLesson(lessonId,publisherUsername);
        //return ResponseEntity.noContent().build();
        var msg = "deleted successfully";
        return ResponseEntity.ok(msg);
    }

    @GetMapping
    public ResponseEntity<PageResponse<LessonResponse>> findAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "20", required = false) int size) {
        PageResponse<LessonResponse> lessonsPage = lessonService.getAllLessons(page, size);
        return ResponseEntity.ok(lessonsPage);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('CANDIDATE')")
    public ResponseEntity<PageResponse<LessonResponse>> findLessonByTitleContaining(
            @RequestParam(name = "keyword") String keyword,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "20", required = false) int size) {
        PageResponse<LessonResponse> lessonsPage = lessonService.findLessonByTitleContaining(keyword, page, size);
        return ResponseEntity.ok(lessonsPage);
    }
}
