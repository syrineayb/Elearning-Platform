package com.pfe.elearning.domain.dto.response;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DomainResponse {
    private Integer id;
    private String title;
}
