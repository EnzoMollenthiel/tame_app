package com.tame.tameApi.users;

import com.tame.tameApi.users.DAOs.UsersDao;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    private final UsersDao usersDao = new UsersDao();

}
