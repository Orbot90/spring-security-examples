package ru.orbot90.security.encoding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.orbot90.security.encoding.model.User;
import ru.orbot90.security.encoding.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by plevako on 11.05.2016.
 */
@Controller
public class SecurityExampleEncodingController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage() {
        return "main";
    }

    @RequestMapping(value = "/loginpage", method = RequestMethod.GET)
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String join() {
        return "registration";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String register(HttpServletRequest request) {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        userService.registerUser(user);
        return "redirect:/secured";
    }

    @RequestMapping(value = "/secured")
    public String securedPage(ModelMap modelMap) {
        List<User> userList = userService.getAllUsers();
        modelMap.addAttribute("users", userList);
        return "secured";
    }
}
