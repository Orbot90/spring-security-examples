package ru.orbot90.security.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

/**
 * Created by plevako on 04.05.2016.
 */
@Service
public class MockServiceImpl implements MockService {

    @Override
    public void unsecuredMethod() {

    }

    @Secured("ROLE_USER")
    @Override
    public void securedMethod() {

    }

    @Secured({"ROLE_ADMIN"})
    @Override
    public void adminMethod() {

    }
}
