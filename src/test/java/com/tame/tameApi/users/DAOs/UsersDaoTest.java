package com.tame.tameApi.users.DAOs;

import com.tame.tameApi.users.DTOs.UserDtoIn;
import com.tame.tameApi.users.exceptions.InvalidEmailFormatException;
import com.tame.tameApi.users.exceptions.NilIdException;
import com.tame.tameApi.users.exceptions.ToOldException;
import com.tame.tameApi.users.exceptions.ToYoungException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void save_should_create_a_new_user() throws InvalidEmailFormatException, ToYoungException, ToOldException {
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

    @Test(expected = NilIdException.class)
    public void delete_should_throw_exception_if_id_does_not_exist() {
        Long nilId = Long.parseLong("4345");
        usersDao.deleteById(nilId);
    }

    @AfterEach
    void clear() {
        usersRepository.delete(this.user.getId());
        this.user = null;
    }

    @Test
    public void save_should_throw_Exception_if_email_has_invalid_format() {
        Date date = new GregorianCalendar(1990, Calendar.FEBRUARY, 11).getTime();

        UserDtoIn userDtoIn = new UserDtoIn();
        userDtoIn.setEmail("tata");
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

        assertThrows(InvalidEmailFormatException.class, () -> usersDao.save(userDtoIn));
    }

    @Test
    public void save_user_should_have_at_least_ten_years_difference_with_min_age_if_younger() {
        Date date = new GregorianCalendar(1990, Calendar.FEBRUARY, 11).getTime();

        UserDtoIn userDtoIn = new UserDtoIn();
        userDtoIn.setEmail("test@test.fr");
        userDtoIn.setPseudo("pseudo");
        userDtoIn.setPassword("super test password");
        userDtoIn.setAgeMax(40);
        userDtoIn.setAgeMin(35);
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

        assertThrows(ToYoungException.class, () -> usersDao.save(userDtoIn));
    }

    @Test
    public void save_user_should_have_at_least_ten_years_difference_with_max_age_if_older() {
        Date date = new GregorianCalendar(1980, Calendar.FEBRUARY, 11).getTime();

        UserDtoIn userDtoIn = new UserDtoIn();
        userDtoIn.setEmail("test@test.fr");
        userDtoIn.setPseudo("pseudo");
        userDtoIn.setPassword("super test password");
        userDtoIn.setAgeMax(35);
        userDtoIn.setAgeMin(30);
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

        assertThrows(ToOldException.class, () -> usersDao.save(userDtoIn));
    }

    @Test
    public void update_should_update_user_in_database() throws InvalidEmailFormatException, ToYoungException, ToOldException {
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

        usersDao.update(this.user.getId(), userDtoIn);

        assertEquals("Should update a user from UserDtoIn object",
                this.user.getId(),
                usersRepository.findUserByEmail(userDtoIn.getEmail()).getId());
    }

    public void update_should_throw_Exception_if_email_has_invalid_format() throws InvalidEmailFormatException {
        Date date = new GregorianCalendar(1990, Calendar.FEBRUARY, 11).getTime();

        UserDtoIn userDtoIn = new UserDtoIn();
        userDtoIn.setEmail("tata");
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

        assertThrows(InvalidEmailFormatException.class, () -> usersDao.update(this.user.getId(), userDtoIn));
    }

    @Test
    public void update_user_should_have_at_least_ten_years_difference_with_min_age_if_younger() {
        Date date = new GregorianCalendar(1990, Calendar.FEBRUARY, 11).getTime();

        UserDtoIn userDtoIn = new UserDtoIn();
        userDtoIn.setEmail("test@test.fr");
        userDtoIn.setPseudo("pseudo");
        userDtoIn.setPassword("super test password");
        userDtoIn.setAgeMax(40);
        userDtoIn.setAgeMin(35);
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

        assertThrows(ToYoungException.class, () -> usersDao.update(this.user.getId(), userDtoIn));
    }

    @Test
    public void update_user_should_have_at_least_ten_years_difference_with_max_age_if_older() {
        Date date = new GregorianCalendar(1980, Calendar.FEBRUARY, 11).getTime();

        UserDtoIn userDtoIn = new UserDtoIn();
        userDtoIn.setEmail("test@test.fr");
        userDtoIn.setPseudo("pseudo");
        userDtoIn.setPassword("super test password");
        userDtoIn.setAgeMax(35);
        userDtoIn.setAgeMin(30);
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

        assertThrows(ToOldException.class, () -> usersDao.update(this.user.getId(), userDtoIn));
    }
}
