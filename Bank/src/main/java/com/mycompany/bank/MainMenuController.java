/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Nick-PC
 */
@Controller
@EnableAutoConfiguration
public class MainMenuController {

    @Autowired
    private UserService userService;
    
    private Authentication auth;
    
    private Validator validator;

    private User user;

    public void validate() {
        ValidatorFactory vF = Validation.buildDefaultValidatorFactory();
        validator = vF.getValidator();
        auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
      // System.out.println(userName);
        user = userService.findUserByEmail(userName);
    }

    @RequestMapping(value = "/mainMenu", method = RequestMethod.GET)
    public String mainMenu(Model m) {
        validate();
        m.addAttribute("user", user);
        return "Main Menu";
    }
    
    @RequestMapping(value="/viewstats")
    public String viewStats (Model m){
        m.addAttribute("user", user);
        return "View Stats";
    }
}
