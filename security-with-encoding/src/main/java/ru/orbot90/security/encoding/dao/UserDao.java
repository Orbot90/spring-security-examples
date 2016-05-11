package ru.orbot90.security.encoding.dao;

import ru.orbot90.security.encoding.model.User;

import java.util.List;

/**
 * Created by plevako on 11.05.2016.
 */
public interface UserDao {
    void save(User user);

    List<User> findAll();

    User findOne(String userName);

    boolean exists(String username);
}
