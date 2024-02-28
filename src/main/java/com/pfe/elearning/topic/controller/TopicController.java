package com.pfe.elearning.topic.controller;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.topic.dto.request.TopicRequest;
import com.pfe.elearning.topic.dto.response.TopicResponse;
import com.pfe.elearning.topic.service.TopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<TopicResponse> save(@RequestBody @Valid TopicRequest topicRequest) {
        return ResponseEntity.ok(topicService.createTopic(topicRequest));
    }

    @GetMapping("/{topicId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<TopicResponse> findById(@PathVariable("topicId") Long topicId) {
        TopicResponse topicResponse = topicService.findById(topicId);
        if (topicResponse != null && topicResponse.getId() != null) {
            return ResponseEntity.ok(topicResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<PageResponse<TopicResponse>> findAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "4", required = false) int size) {
        return ResponseEntity.ok(topicService.findAll(page, size));
    }

    @PutMapping("/{topicId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> update(
            @PathVariable("topicId") Long topicId,
            @RequestBody @Valid TopicRequest topicRequest) {
        try {
            TopicResponse updatedTopic = topicService.update(topicId, topicRequest);
            return ResponseEntity.ok(updatedTopic);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Topic not found with id: " + topicId);
        }
    }

    @DeleteMapping("/{topicId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> deleteById(@PathVariable("topicId") Long topicId) {
        if (topicService.existsById(topicId)) {
            topicService.deleteById(topicId);
            return ResponseEntity.ok("Topic " + topicId + " successfully deleted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    @PreAuthorize("permitAll()")
    public ResponseEntity<PageResponse<TopicResponse>> findByTitleContaining(
            @RequestParam(name = "keyword") String keyword,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "20", required = false) int size) {
        return ResponseEntity.ok(topicService.findByTitleContaining(keyword, page, size));
    }

}
