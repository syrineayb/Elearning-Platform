package com.pfe.elearning.topic.dto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopicResponse {
    private Integer id;
    private String title;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    public TopicResponse(Integer id, String title, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
