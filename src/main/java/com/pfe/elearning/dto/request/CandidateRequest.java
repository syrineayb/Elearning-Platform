package com.pfe.elearning.dto.request;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateRequest {

    private Integer id;
    @NotNull(message = "Firstname must not be null")
    private String firstname;
    @NotNull(message = "Lastname must not be null")
    private String lastname;
    @NotNull(message = "Age must not be null")
    @Positive(message = "Age must be positive")
    @Min(value = 18, message = "Candidate must be at least 18 years old")
    private int age;
}