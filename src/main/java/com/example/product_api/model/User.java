package com.example.product_api.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD_HASH")
    private String passwordHash;

    @Column(name = "SALT")
    private String salt;

    @ManyToOne
    @JoinColumn(name = "AUTHORIZATION_LEVEL_ID", referencedColumnName = "ID")
    private AuthorizationLevel authorizationLevel;

    // Getters and setters
}
