package ru.orbot90.security.authclient.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import ru.orbot90.security.auth.api.model.AuthenticationRequest;
import ru.orbot90.security.auth.api.model.AuthenticationResponse;
import ru.orbot90.security.auth.api.service.AuthenticationService;

import java.util.Arrays;

/**
 * Created by plevako on 10.05.2016.
 */
@Service
public class SecurityAuthServerAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        AuthenticationRequest request = new AuthenticationRequest();
        request.setUsername(username);
        request.setPassword(password);
        AuthenticationResponse response = authenticationService.authenticate(request);
        if(response.getSuccess()) {
            return new UsernamePasswordAuthenticationToken(username, password,
                    Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
