package ru.orbot90.security.encoding.model;

import lombok.Data;

import java.util.List;

/**
 * Created by plevako on 11.05.2016.
 */
@Data
public class User {
    private String username;
    private String password;
    private List<String> roles;
}
