package com.tame.tameApi.users.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "pseudo", nullable = false)
    private String pseudo;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "age_max", nullable = true)
    private Integer ageMax;

    @Column(name = "age_min", nullable = true)
    private Integer ageMin;

    @Column(name = "distance", nullable = false)
    private Integer distance;

    @Column(name = "matches_number", nullable = false)
    private Integer matchesNumber;

    @Column(name = "been_liked_number", nullable = false)
    private Integer beenLikedNumber;

    @Column(name = "been_disliked_number", nullable = false)
    private Integer beenDislikedNumber;

    @Column(name = "did_like_number", nullable = false)
    private Integer didLikeNumber;

    @Column(name = "did_dislike_number", nullable = false)
    private Integer didDislikeNumber;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "city", nullable = false)
    private String city;
}
