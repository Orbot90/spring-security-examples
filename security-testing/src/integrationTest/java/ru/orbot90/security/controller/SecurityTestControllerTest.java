package ru.orbot90.security.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.orbot90.security.config.ApplicationConfig;

import javax.servlet.Filter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by plevako on 04.05.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@WebAppConfiguration
public class SecurityTestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    @Test
    public void testUnsecuredController() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    public void testSecuredController() throws Exception {
        mockMvc.perform(get("/secured"))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(header().string("Location", "http://localhost/login"));
    }

    @Test
    public void testSecuredControllerWithUser() throws Exception {
        mockMvc.perform(get("/secured").with(user("user").roles("USER")))
                .andExpect(status().isOk());
    }

    @Test
    public void testSecuredAdminControllerMethod() throws Exception {
        mockMvc.perform(get("/admin").with(user("user").roles("USER")))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    public void testSecuredAdminControllermethodWithUserAdmin() throws Exception {
        mockMvc.perform(get("/admin").with(user("user").roles("ADMIN")))
                .andExpect(status().isOk());
    }
}
