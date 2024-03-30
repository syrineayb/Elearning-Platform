package com.pfe.elearning.topic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class TopicRequest {
    @NotBlank(message = "Title cannot be blank")
    private String title;
    private String imageUrl;
    public TopicRequest(String title) {
        this.title = title;
    }

}
