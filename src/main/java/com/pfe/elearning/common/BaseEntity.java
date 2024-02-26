package com.pfe.elearning.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pfe.elearning.genre.entity.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

/*@Getter
@Setter
@SuperBuilder
@MappedSuperclass

 */
@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
   /*
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    protected LocalDateTime createdAt = LocalDateTime.now();
    @NotBlank
    @Column(nullable = false)
    @Size(min = 2,max =100)
    protected String firstname;

    @NotBlank
    @Column(nullable = false)
    @Size(min = 2,max =100)
    protected String lastname;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    protected String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    Genre genre;

    protected boolean enabled;


    */



}