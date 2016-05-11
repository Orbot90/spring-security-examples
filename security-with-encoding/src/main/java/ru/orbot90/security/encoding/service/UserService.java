package ru.orbot90.security.encoding.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.orbot90.security.encoding.model.User;

import java.util.List;

/**
 * Created by plevako on 11.05.2016.
 */
public interface UserService extends UserDetailsService {
    void registerUser(ru.orbot90.security.encoding.model.User user);

    List<User> getAllUsers();
}
