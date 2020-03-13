package com.tame.tameApi.users;

import com.tame.tameApi.users.DAOs.UsersDao;
import com.tame.tameApi.users.DTOs.UserDtoIn;
import com.tame.tameApi.users.exceptions.InvalidEmailFormatException;
import com.tame.tameApi.users.exceptions.ToOldException;
import com.tame.tameApi.users.exceptions.ToYoungException;
import com.tame.tameApi.users.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {

    private final UsersDao usersDao = new UsersDao();

    @PostMapping(value = "/user/{id}")
    public User readOne(@PathVariable Long id) {
        return usersDao.getById(id);
    }

    @PostMapping(value = "/user")
    public User create(@RequestBody UserDtoIn userDtoIn) throws InvalidEmailFormatException, ToYoungException, ToOldException {
        return usersDao.save(userDtoIn);
    }

    @PutMapping(value = "/user/{id}")
    public User update(@PathVariable Long id, @RequestBody UserDtoIn userDtoIn) throws InvalidEmailFormatException, ToYoungException, ToOldException {
        return usersDao.update(id, userDtoIn);
    }

    @GetMapping(value = "/users")
    public List<User> readAll() {
        return usersDao.getAll();
    }

    @DeleteMapping(value = "/user/{id}")
    public void delete(@PathVariable Long id) {
        usersDao.deleteById(id);
    }

}
