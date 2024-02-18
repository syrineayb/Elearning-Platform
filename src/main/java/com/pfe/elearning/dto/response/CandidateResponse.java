package com.pfe.elearning.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Builder
public class CandidateResponse {

    private String firstname;
    private String lastname;
    private int age;
    private int nbrSubjects;
}