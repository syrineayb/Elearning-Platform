package com.pfe.elearning.domain.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
public class DomainRequest {
    private Long id;

    @NotNull(message = "100")
    @NotEmpty(message = "100")
    private String title;
}
