package ru.orbot90.security.auth.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by plevako on 10.05.2016.
 */
@Data
public class AuthenticationRequest implements Serializable {
    private String username;
    private String password;
}
