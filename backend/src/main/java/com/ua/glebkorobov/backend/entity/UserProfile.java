package com.ua.glebkorobov.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users_profiles")
public class UserProfile {

    @Id
    private Integer id;

    @Column(unique = true)
    private Long accountId;
}




