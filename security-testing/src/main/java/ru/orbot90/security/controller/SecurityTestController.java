package ru.orbot90.security.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by plevako on 04.05.2016.
 */
@Controller
public class SecurityTestController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String unSecuredControllerMethod(ModelMap modelMap) {
        modelMap.addAttribute("secured", false);
        return "index";
    }

    @RequestMapping(value = "/secured", method = RequestMethod.GET)
    public String securedControllerMethod(ModelMap modelMap) {
        modelMap.addAttribute("secured", true);
        return "index";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String securedAdminControllerMethod(ModelMap modelMap) {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        modelMap.addAttribute("admin", true);
        return "index";
    }
}
