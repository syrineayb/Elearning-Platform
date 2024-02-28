package com.pfe.elearning.admin.entity;

import com.pfe.elearning.profile.entity.Profile;
import com.pfe.elearning.user.entity.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
@Getter
@Setter
@Entity
@DiscriminatorValue("Admin")
@Table(name = "admins")
public class Admin extends User {
    //private int xxx;

}
