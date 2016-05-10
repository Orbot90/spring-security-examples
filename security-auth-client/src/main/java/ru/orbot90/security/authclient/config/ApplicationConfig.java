package ru.orbot90.security.authclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.orbot90.security.auth.api.service.AuthenticationService;

/**
 * Created by plevako on 10.05.2016.
 */
@Configuration
@ComponentScan(basePackages = "ru.orbot90")
@EnableWebMvc
public class ApplicationConfig {
    @Bean
    public HessianProxyFactoryBean accountService() {
        HessianProxyFactoryBean proxyFactoryBean = new HessianProxyFactoryBean();
        proxyFactoryBean.setServiceUrl("http://localhost:8080/authserver/AuthService");
        proxyFactoryBean.setServiceInterface(AuthenticationService.class);
        return proxyFactoryBean;
    }
}
