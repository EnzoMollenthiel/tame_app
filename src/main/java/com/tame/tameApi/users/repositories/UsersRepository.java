package com.tame.tameApi.users.repositories;

import com.tame.tameApi.users.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<User,Long> {

    List<User> findAll();
}

