package ru.orbot90.security.auth.api.service;

import ru.orbot90.security.auth.api.model.AuthenticationRequest;
import ru.orbot90.security.auth.api.model.AuthenticationResponse;

/**
 * Created by plevako on 10.05.2016.
 */
public interface AuthenticationService {
    String AUTH_SERVICE_QUEUE = "authService";

    AuthenticationResponse authenticate(AuthenticationRequest req);
}
