package ru.orbot90.security.authclient.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by plevako on 10.05.2016.
 */
@Configuration
@ComponentScan(basePackages = "ru.orbot90")
@EnableWebMvc
public class ApplicationConfig {
}
