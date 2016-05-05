package ru.orbot90.security.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.orbot90.security.config.ApplicationConfig;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Test(expected = AccessDeniedException.class)
    @WithAnonymousUser
    public void testCallSecuredMethod() {
        mockService.securedMethod();
    }

    /**
     * С @WithMockUser можно задать роль юзера, либо его юзернейм,
     * юзер не обязан существовать в системе
     */
    @Test
    @WithMockUser(roles = "USER")
    public void testCallSecuredMethodWithUser() {
        mockService.securedMethod();
    }

    /**
     * @WithUserDetails позволяет перед запуском теста залогиниться
     * под юзером с заданным именем. Юзер с заданным именем должен существовать в системе
     */
    @Test(expected = AccessDeniedException.class)
    @WithUserDetails("cooluser")
    public void testCallAdminMethodWithUser() {
        mockService.adminMethod();
    }

    @Test
    @WithMockCustomUser(username = "someAdmin", password = "adminPass", roles = {"ROLE_ADMIN"})
    public void testCallAdminMethodWithAdmin() {
        mockService.adminMethod();
    }
}

/**
 * Кастомная аннотация, использующая @WithSecurityContext
 * позволяет запустить тест с кастомным токеном в SecurityContext,
 * который может содержать любую необходимую информацию, которую содержит
 * кастомная реализация токена, но не содержит стандартная
 */
@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
@interface WithMockCustomUser {
    String username() default "user";
    String password() default "password";
    String[] roles() default {};
}

class WithMockCustomUserSecurityContextFactory
    implements WithSecurityContextFactory<WithMockCustomUser> {

    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser annotation) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        List<GrantedAuthority> authorities = Arrays.asList(annotation.roles())
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User user = new User(annotation.username(), annotation.password(), authorities);
        Authentication auth
                = new UsernamePasswordAuthenticationToken(user, annotation.password(), user.getAuthorities());
        context.setAuthentication(auth);
        return context;
    }
}