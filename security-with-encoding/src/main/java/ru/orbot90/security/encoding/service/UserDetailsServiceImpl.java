package ru.orbot90.security.encoding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.orbot90.security.encoding.dao.UserDao;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by plevako on 11.05.2016.
 */
@Service
public class UserDetailsServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return createUser(userDao.findOne(username));
    }

    @Override
    public void registerUser(ru.orbot90.security.encoding.model.User user) {
        if(!userDao.exists(user.getUsername())) {
            user.setRoles(Arrays.asList("ROLE_USER"));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.save(user);
        }
    }

    @Override
    public List<ru.orbot90.security.encoding.model.User> getAllUsers() {
        return userDao.findAll();
    }

    private User createUser(ru.orbot90.security.encoding.model.User user) {
        if(user == null)
            throw new UsernameNotFoundException("User not found");
        List<GrantedAuthority> authorities = getAuthorities(user);
        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    private List<GrantedAuthority> getAuthorities(ru.orbot90.security.encoding.model.User user) {
        return user.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
