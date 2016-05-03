package ru.orbot90.security.console.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

/**
 * Created by root on 04.05.16.
 */
@Service
public class MockServiceImpl implements MockService {

    @Secured("ROLE_USER")
    @Override
    public void securedMethod() {
        System.out.println("Called secured!");
    }

    @Override
    public void unSecuredMethod() {
        System.out.println("Called unsecured!");
    }
}
