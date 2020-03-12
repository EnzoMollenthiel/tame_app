package com.tame.tameApi.users.repositories.impl;

import com.tame.tameApi.users.DTOs.UserDtoIn;
import com.tame.tameApi.users.models.User;
import com.tame.tameApi.users.repositories.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private static UsersRepository usersRepository;

    @Test
    public void save_should_create_user_in_database() {

        Date date = new GregorianCalendar(1990, Calendar.FEBRUARY, 11).getTime();

        User user = new User();
        user.setEmail("test@test.fr");
        user.setPseudo("test pseudo");
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

        User newUser = usersRepository.save(user);

        assertNotNull(newUser);
    }
}
