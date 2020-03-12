package com.tame.tameApi.users;

import com.tame.tameApi.users.models.User;
import com.tame.tameApi.users.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/users")
    List<User> getAll() {
        return usersRepository.findAll();
    }

}
