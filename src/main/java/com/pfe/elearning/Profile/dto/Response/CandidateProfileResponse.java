package com.pfe.elearning.Profile.dto.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateProfileResponse {

    private String firstName;
    private String lastName;

    private int age;
    // Ajoutez d'autres champs nécessaires pour la réponse du profil du candidat
}