package ru.orbot90.security.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.orbot90.security.console.config.ApplicationConfig;
import ru.orbot90.security.console.service.LoginService;
import ru.orbot90.security.console.service.MockService;

import java.util.Scanner;

/**
 * Created by root on 03.05.16.
 */
@Component
public class Application {

    @Autowired
    private LoginService loginService;
    @Autowired
    private MockService mockService;

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ApplicationConfig.class);
        context.getBean(Application.class).start(args);
    }

    public void start(String[] args) {
        callMethods();
        login();
        callMethods();
    }

    private void login() {
        Scanner stdinScanner = new Scanner(System.in);
        System.out.print("Login: ");
        String login = stdinScanner.nextLine();
        System.out.print("Password: ");
        String password = stdinScanner.nextLine();
        System.out.println(
                loginService.login(login, password) ? "Login success" : "Login failed");
    }

    private void callMethods() {
        System.out.println("=========Calling mock service methods============");
        mockService.securedMethod();
        mockService.unSecuredMethod();
        System.out.println("=========Done calling mock service===========");
    }
}
