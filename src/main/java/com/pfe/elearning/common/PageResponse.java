package com.pfe.elearning.common;

import com.pfe.elearning.lesson.dto.LessonResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {

    private List<T> content;
    private Integer totalPages;
    private Long totalElements; // Adding the totalElements field


}
