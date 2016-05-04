package ru.orbot90.security.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.orbot90.security.config.ApplicationConfig;

/**
 * Created by plevako on 04.05.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        WithSecurityContextTestExecutionListener.class})
@WebAppConfiguration
public class MockServiceTest {

    @Autowired
    private MockService mockService;

    @Test
    public void testCallUnsecuredMethod() {
        mockService.unsecuredMethod();
    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testCallSecuredMethod() {
        mockService.securedMethod();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testCallSecuredMethodWithUser() {
        mockService.securedMethod();
    }

    @Test(expected = AccessDeniedException.class)
    @WithMockUser(roles = "USER")
    public void testCallAdminMethodWithUser() {
        mockService.adminMethod();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testCallAdminMethodWithAdmin() {
        mockService.adminMethod();
    }
}
