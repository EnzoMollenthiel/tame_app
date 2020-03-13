package com.tame.tameApi.users.DAOs;

import com.tame.tameApi.users.models.User;
import com.tame.tameApi.users.repositories.UsersRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersDaoTest {

    @Autowired
    private UsersRepository usersRepository;

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

    @AfterEach
    void clear() {
        usersRepository.delete(this.user.getId());
        this.user = null;
    }

    @Test
    public void getById_should_return_a_user() {
        User foundUser = UsersDao.getById(this.user.getId());

        assertEquals("Should find and return a user by it's id", this.user, foundUser);
    }
}
