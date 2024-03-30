package com.pfe.elearning.topic.controller;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.course.dto.CourseResponse;
import com.pfe.elearning.exception.TopicNotFoundException;
import com.pfe.elearning.exception.TopicValidationException;
import com.pfe.elearning.topic.dto.TopicRequest;
import com.pfe.elearning.topic.dto.TopicResponse;
import com.pfe.elearning.topic.service.TopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public  ResponseEntity<Void> save(@RequestBody @Valid TopicRequest topicRequest) {
        topicService.createTopic(topicRequest);
        return ResponseEntity.accepted().build();
       // return ResponseEntity.ok(topicService.createTopic(topicRequest));
    }

    @GetMapping("/{topicId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<TopicResponse> findById(@PathVariable("topicId") Integer topicId) {
        TopicResponse topicResponse = topicService.findById(topicId);
        if (topicResponse != null && topicResponse.getId() != null) {
            return ResponseEntity.ok(topicResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/findAll")
    public ResponseEntity<PageResponse<TopicResponse>> findAll(
            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
            @RequestParam(name = "size", defaultValue = "4", required = false) int size) {
        return ResponseEntity.ok(topicService.findAll(page, size));
    }
    @GetMapping
    public ResponseEntity<List<TopicResponse>> getAllTopics() {
        List<TopicResponse> topics = topicService.getAllTopics();
        return ResponseEntity.ok(topics);
    }


    @PutMapping("/{topicId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> update(
            @PathVariable("topicId") Integer topicId,
            @RequestBody @Valid TopicRequest topicRequest) {
        try {
            TopicResponse updatedTopic = topicService.update(topicId, topicRequest);
            return ResponseEntity.ok(updatedTopic);
        } catch (TopicNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (TopicValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{topicId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> deleteById(@PathVariable("topicId") Integer topicId) {
        if (topicService.existsById(topicId)) {
            topicService.deleteById(topicId);
            return ResponseEntity.ok("Topic " + topicId + " successfully deleted.");
        } else {
            throw new TopicNotFoundException(topicId);
        }
    }


    @GetMapping("/search")
    //@PreAuthorize("permitAll()")
    public ResponseEntity<PageResponse<TopicResponse>> findByTitleContaining(
            @RequestParam(name = "keyword") String keyword,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "20", required = false) int size) {
        return ResponseEntity.ok(topicService.findByTitleContaining(keyword, page, size));
    }

}
