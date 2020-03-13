package com.tame.tameApi.users.DAOs;

import com.tame.tameApi.users.DTOs.UserDtoIn;
import com.tame.tameApi.users.exceptions.InvalidEmailFormatException;
import com.tame.tameApi.users.exceptions.NilIdException;
import com.tame.tameApi.users.exceptions.ToYoungException;
import com.tame.tameApi.users.models.User;
import com.tame.tameApi.users.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class UsersDao {

    @Autowired
    UsersRepository usersRepository;

    private static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

    public User getById(Long id) {
        return usersRepository.findUserById(id);
    }

    public User save(UserDtoIn userDtoIn) throws InvalidEmailFormatException, ToYoungException {
        User user = new User(userDtoIn);

        long userAge = new Date().getYear() - user.getBirthDate().getYear();

        if(!user.getEmail().matches(EMAIL_REGEX)) throw new InvalidEmailFormatException();
        else if(user.getAgeMin() > userAge &&
                ((user.getAgeMin() - userAge) < 10)) throw new ToYoungException();

        return usersRepository.save(user);
    }

    public User update(Long id, UserDtoIn userDtoIn) throws InvalidEmailFormatException {
        User user = new User(userDtoIn);
        user.setId(id);

        if(!user.getEmail().matches(EMAIL_REGEX)) throw new InvalidEmailFormatException();

        return usersRepository.save(user);
    }

    public List<User> getAll() {
        return usersRepository.findAll();
    }

    public void deleteById(Long id) {
        User foundUser = this.getById(id);
        if (foundUser == null) throw new NilIdException();
        usersRepository.delete(id);

    }
}
