/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Nick-PC
 */
@Controller
@EnableAutoConfiguration
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login() {
        return "Login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model m) {
        User u = new User();
        m.addAttribute("user", u);
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createUser(@Valid User u, BindingResult result, Model m) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userExists = userService.findUserByEmail(auth.getName());
        if (userExists != null) {
            result
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (result.hasErrors()) {
            return "registration";
        } else {

            userService.saveUser(u);
            m.addAttribute("successMessage", "User has been created successfully!");
            m.addAttribute("user", new User());
        }
        return "registration";
    }

    @RequestMapping(value = "/mainmenu", method = RequestMethod.GET)
    public ModelAndView mainMenu() {
        ModelAndView newView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        newView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
        newView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        newView.setViewName("Main Menu");

        return newView;
    }

}
