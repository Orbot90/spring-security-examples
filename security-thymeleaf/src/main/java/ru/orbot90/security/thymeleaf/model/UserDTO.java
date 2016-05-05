package ru.orbot90.security.thymeleaf.model;

import lombok.Data;

/**
 * Created by plevako on 05.05.2016.
 */
@Data
public class UserDTO {
    private String username;
    private String password;
    private String role;
}

