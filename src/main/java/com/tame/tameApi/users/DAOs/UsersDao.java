package com.tame.tameApi.users.DAOs;

import com.tame.tameApi.users.DTOs.UserDtoIn;
import com.tame.tameApi.users.models.User;
import com.tame.tameApi.users.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersDao {

    @Autowired
    UsersRepository usersRepository;

    public User getById(Long id) {
        return usersRepository.findUserById(id);
    }

    public User save(UserDtoIn userDtoIn) {
        User user = new User(null,
                userDtoIn.getEmail(),
                userDtoIn.getPseudo(),
                userDtoIn.getPassword(),
                userDtoIn.getPhoneNumber(),
                userDtoIn.getBirthDate(),
                userDtoIn.getAgeMax(),
                userDtoIn.getAgeMin(),
                userDtoIn.getDistance(),
                userDtoIn.getMatchesNumber(),
                userDtoIn.getBeenLikedNumber(),
                userDtoIn.getBeenDislikedNumber(),
                userDtoIn.getDidLikeNumber(),
                userDtoIn.getDidDislikeNumber(),
                userDtoIn.getDescription(),
                userDtoIn.getCity());

        return usersRepository.save(user);
    }
}
