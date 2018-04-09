/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Nick-PC
 */
@Controller
@EnableAutoConfiguration
public class LoginController {

    @Autowired
    private UserService userService;

    private Bank bank;

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
    public String mainMenu() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.isAuthenticated());
        User user = userService.findUserByEmail(auth.getName());
        String query = "Select * from user where email=? and password=?";
        PreparedStatement pstmt;
        try {
            pstmt = bank.connect().prepareStatement(query);
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.executeUpdate();
            bank.connect().commit();
            return "Main Menu";
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!user.equals(null) && user.getMoney() == 0 && user.getJob().equals(null)) {
            return "Choose Car";
        } else {
            return "Login";
        }

    }

}
