package com.tame.tameApi.users.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoOut {
    private String email;
    private String pseudo;
    private String phoneNumber;
    private Date birthDate;
    private Integer ageMax;
    private Integer ageMin;
    private Integer distance;
    private Integer matchesNumber;
    private Integer beenLikedNumber;
    private Integer beenDislikedNumber;
    private Integer didLikeNumber;
    private Integer didDislikeNumber;
    private String description;
    private String city;
}
