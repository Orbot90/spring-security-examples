package ru.orbot90.security.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by orbot on 03.05.16.
 */
@Configuration
@ComponentScan(basePackages = {"ru.orbot90"})
@EnableWebMvc
public class ApplicationConfig {
}
