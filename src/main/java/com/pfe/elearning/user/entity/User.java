package com.pfe.elearning.user.entity;

import com.pfe.elearning.profile.entity.Profile;
import com.pfe.elearning.genre.entity.Genre;
import com.pfe.elearning.role.entity.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static jakarta.persistence.DiscriminatorType.STRING;
import static jakarta.persistence.InheritanceType.SINGLE_TABLE;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "users")
//@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = STRING)
//@Inheritance(strategy = InheritanceType.JOINED)
//@JsonIgnoreProperties(value = {"createdAt"},allowGetters = true)

public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   /* @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();*/
    @NotBlank
    @Column(nullable = false)
    @Size(min = 2,max =100)
    private String firstname;

    @NotBlank
    @Column(nullable = false)
    @Size(min = 2,max =100)
    private String lastname;

    @Email(message = "Email not valid")
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Genre genre;

   /* @OneToOne
    private Profile Profile;

    */
   @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private Profile profile;

    // @NotBlank
   // @Column(nullable = false)
   // private String address,phoneNumber;

    private boolean enabled;
    private boolean active;

   // @ManyToMany(fetch = FetchType.EAGER)
   @ManyToMany(fetch = FetchType.EAGER)
   private List<Role> roles;

    //@JoinColumn(name = "role_id")
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

      @Override
    public boolean isEnabled() {
        return enabled;
    }


}