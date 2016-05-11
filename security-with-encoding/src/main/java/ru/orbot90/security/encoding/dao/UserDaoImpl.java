package ru.orbot90.security.encoding.dao;

import org.springframework.stereotype.Repository;
import ru.orbot90.security.encoding.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by plevako on 11.05.2016.
 */
@Repository
public class UserDaoImpl implements UserDao {

    private Map<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getUsername(), user);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User findOne(String userName) {
        return users.get(userName);
    }

    @Override
    public boolean exists(String username) {
        return users.containsKey(username);
    }
}
