package ru.orbot90.security.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import ru.orbot90.security.auth.api.service.AuthenticationService;

/**
 * Created by orbot on 10.05.16.
 */
@Configuration
public class HessianConfig {

    @Autowired
    private AuthenticationService authenticationService;

    @Bean(name = "/AuthService")
    public HessianServiceExporter authServiceExporter() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(authenticationService);
        exporter.setServiceInterface(AuthenticationService.class);
        return exporter;
    }
}
