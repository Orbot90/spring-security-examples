package ru.orbot90.security.server.service;

import org.springframework.stereotype.Service;
import ru.orbot90.security.auth.api.model.AuthenticationRequest;
import ru.orbot90.security.auth.api.model.AuthenticationResponse;
import ru.orbot90.security.auth.api.service.AuthenticationService;

/**
 * Created by plevako on 10.05.2016.
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest req) {
        boolean success = req.getUsername().equals("cooluser")
                && req.getPassword().equals("password");
        AuthenticationResponse response = new AuthenticationResponse();
        response.setSuccess(success);
        return response;
    }
}
