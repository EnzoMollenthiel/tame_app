package com.tame.tameApi.users.DAOs;

import com.tame.tameApi.users.DTOs.UserDtoIn;
import com.tame.tameApi.users.exceptions.InvalidEmailFormatException;
import com.tame.tameApi.users.models.User;
import com.tame.tameApi.users.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersDao {

    @Autowired
    UsersRepository usersRepository;

    public User getById(Long id) {
        return usersRepository.findUserById(id);
    }

    public User save(UserDtoIn userDtoIn) throws InvalidEmailFormatException {
        User user = new User(userDtoIn);
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

        if(!user.getEmail().matches(regex)) throw new InvalidEmailFormatException();

        return usersRepository.save(user);
    }
}
