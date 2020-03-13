package com.tame.tameApi.users.DAOs;

import com.tame.tameApi.users.DTOs.UserDtoIn;
import com.tame.tameApi.users.models.User;
import com.tame.tameApi.users.repositories.UsersRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersDaoTest {

    @Autowired
    private UsersRepository usersRepository;

    private UsersDao usersDao = new UsersDao();
    private User user;

    @BeforeEach
    void init() {
        User user = new User();
        user.setEmail("test.test@test.fr");
        user.setPseudo("pseudo test");
        user.setPassword("super test password");
        user.setAgeMax(90);
        user.setAgeMin(80);
        user.setPhoneNumber("065235648987");
        user.setCity("Bordeaux");
        user.setBirthDate(new Date());
        user.setDistance(15);
        user.setBeenDislikedNumber(0);
        user.setBeenLikedNumber(0);
        user.setDidLikeNumber(0);
        user.setDidDislikeNumber(0);
        user.setMatchesNumber(0);
        user.setDescription("test description");

        this.user = usersRepository.save(user);
    }

    @Test
    public void getById_should_return_a_user() {
        User foundUser = usersDao.getById(this.user.getId());

        assertEquals("Should find and return a user by its id", this.user, foundUser);
    }

    @Test
    public void save_should_create_a_new_user() {
        Date date = new GregorianCalendar(1990, Calendar.FEBRUARY, 11).getTime();

        UserDtoIn userDtoIn = new UserDtoIn();
        userDtoIn.setEmail("test@test.fr");
        userDtoIn.setPseudo("pseudo");
        userDtoIn.setPassword("super test password");
        userDtoIn.setAgeMax(90);
        userDtoIn.setAgeMin(80);
        userDtoIn.setPhoneNumber("065235648987");
        userDtoIn.setCity("Bordeaux");
        userDtoIn.setBirthDate(date);
        userDtoIn.setDistance(15);
        userDtoIn.setBeenDislikedNumber(0);
        userDtoIn.setBeenLikedNumber(0);
        userDtoIn.setDidLikeNumber(0);
        userDtoIn.setDidDislikeNumber(0);
        userDtoIn.setMatchesNumber(0);
        userDtoIn.setDescription("test description");

        usersDao.save(userDtoIn);

        assertNotNull("Should create a new user from UserDtoIn object",
                usersRepository.findUserByEmail(userDtoIn.getEmail()));
    }

    @Test
    public void get_all_should_not_return_null() {
        List<User> users = usersDao.getAll();
        assertNotNull(users);
    }

    @Test
    public void delete_should_not_find_user_after_delete() {
        usersDao.deleteById(this.user.getId());
        assertNull(usersDao.getById(this.user.getId()));
    }

    @AfterEach
    void clear() {
        usersRepository.delete(this.user.getId());
        this.user = null;
    }
}
