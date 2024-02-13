package com.pfe.elearning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Role {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false,unique = true)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;
    @ManyToMany(mappedBy = "roles")
    private List<User> user;
}
