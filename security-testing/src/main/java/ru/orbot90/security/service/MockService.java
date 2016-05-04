package ru.orbot90.security.service;

/**
 * Created by plevako on 04.05.2016.
 */
public interface MockService {
    void unsecuredMethod();
    void securedMethod();
    void adminMethod();
}
