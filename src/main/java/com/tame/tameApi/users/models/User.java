package com.tame.tameApi.users.models;

import com.tame.tameApi.users.DTOs.UserDtoIn;
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

    public User(UserDtoIn userDtoIn) {
        this.id = null;
        this.email = userDtoIn.getEmail();
        this.pseudo = userDtoIn.getPseudo();
        this.password = userDtoIn.getPassword();
        this.phoneNumber = userDtoIn.getPhoneNumber();
        this.birthDate = userDtoIn.getBirthDate();
        this.ageMax = userDtoIn.getAgeMax();
        this.ageMin = userDtoIn.getAgeMin();
        this.distance = userDtoIn.getDistance();
        this.matchesNumber = userDtoIn.getMatchesNumber();
        this.beenLikedNumber = userDtoIn.getBeenLikedNumber();
        this.beenDislikedNumber = userDtoIn.getBeenDislikedNumber();
        this.didLikeNumber = userDtoIn.getDidLikeNumber();
        this.didDislikeNumber = userDtoIn.getDidDislikeNumber();
        this.description = userDtoIn.getDescription();
        this.city = userDtoIn.getCity();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "pseudo", nullable = false)
    private String pseudo;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number", nullable = false, unique = true)
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
