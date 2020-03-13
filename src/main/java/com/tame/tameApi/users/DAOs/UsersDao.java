package com.tame.tameApi.users.DAOs;

import com.tame.tameApi.users.DTOs.UserDtoIn;
import com.tame.tameApi.users.exceptions.InvalidEmailFormatException;
import com.tame.tameApi.users.exceptions.NilIdException;
import com.tame.tameApi.users.exceptions.TooOldException;
import com.tame.tameApi.users.exceptions.TooYoungException;
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

    public User save(UserDtoIn userDtoIn) throws InvalidEmailFormatException, TooYoungException, TooOldException {
        User user = new User(userDtoIn);

        long userAge = new Date().getYear() - user.getBirthDate().getYear();

        if(!user.getEmail().matches(EMAIL_REGEX)) throw new InvalidEmailFormatException();
        else if(user.getAgeMin() > userAge &&
                ((user.getAgeMin() - userAge) < 10)) throw new TooYoungException();
        else if(user.getAgeMax() < userAge &&
                ((userAge - user.getAgeMax()) < 10)) throw new TooOldException();

        return usersRepository.save(user);
    }

    public User update(Long id, UserDtoIn userDtoIn) throws InvalidEmailFormatException, TooYoungException, TooOldException {
        User user = new User(userDtoIn);
        user.setId(id);

        long userAge = new Date().getYear() - user.getBirthDate().getYear();

        if(!user.getEmail().matches(EMAIL_REGEX)) throw new InvalidEmailFormatException();
        else if(user.getAgeMin() > userAge &&
                ((user.getAgeMin() - userAge) < 10)) throw new TooYoungException();
        else if(user.getAgeMax() < userAge &&
                ((userAge - user.getAgeMax()) < 10)) throw new TooOldException();

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
