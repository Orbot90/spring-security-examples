package ru.orbot90.security.thymeleaf.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.orbot90.security.thymeleaf.model.UserDTO;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by plevako on 05.05.2016.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private Map<String, UserDTO> users = new HashMap<>();

    @PostConstruct
    public void createUsers() {
        UserDTO coolUser = new UserDTO();
        coolUser.setUsername("cooluser");
        coolUser.setPassword("pass");
        coolUser.setRole("ROLE_USER");
        users.put("cooluser", coolUser);

        UserDTO admin = new UserDTO();
        admin.setUsername("admin");;
        admin.setPassword("pass");
        admin.setRole("ROLE_ADMIN");
        users.put("admin", admin);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserDetails(users.get(username));
    }

    private User getUserDetails(UserDTO user) {
        return new User(user.getUsername(), user.getPassword(), Arrays.asList(createAuthority(user)));
    }

    private GrantedAuthority createAuthority(UserDTO user) {
        return new SimpleGrantedAuthority(user.getRole());
    }
}
