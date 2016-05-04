package ru.orbot90.security.model;

import lombok.Data;

/**
 * Created by plevako on 04.05.2016.
 */
@Data
public class UserDTO {
    private String username;
    private String password;
    private String role;
}
