package com.tame.tameApi.users.DAOs;

import com.tame.tameApi.users.DTOs.UserDtoIn;
import com.tame.tameApi.users.models.User;
import com.tame.tameApi.users.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UsersDao {

    @Autowired
    UsersRepository usersRepository;

    public User getById(Long id) {
        return usersRepository.findUserById(id);
    }

    public User save(UserDtoIn userDtoIn) {
        User user = new User(userDtoIn);

        return usersRepository.save(user);
    }

    public List<User> getAll() {
        return usersRepository.findAll();
    }

    public void deleteById(Long id) {
        usersRepository.delete(id);
    }
}
