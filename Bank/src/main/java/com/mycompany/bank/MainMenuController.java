/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank;

import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    private int initialMinute;

    private Double initialBill;

    private LocalDateTime time;

    public void validate() {
        ValidatorFactory vF = Validation.buildDefaultValidatorFactory();
        validator = vF.getValidator();
        auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        // System.out.println(userName);
        user = userService.findUserByEmail(userName);
        initialBill = user.getBillAmount();
    }

    @RequestMapping(value = "/mainMenu", method = RequestMethod.GET)
    public String mainMenu(Model m) {
        validate();
        m.addAttribute("user", user);
        initialMinute = time.getMinute();
        m.addAttribute("minute", initialMinute);
        return "Main Menu";
    }

    @RequestMapping(value = "/viewstats")
    public String viewStats(Model m) {
        check();
        m.addAttribute("user", user);
        return "View Stats";
    }

    @RequestMapping(value = "/bank")
    public String visitBank(Model m) {
        check();
        m.addAttribute("user", user);
        return "Bank";
    }

    @RequestMapping(value = "/bank", method = RequestMethod.POST)
    public String withdrawOrDeposit(Model m, HttpServletRequest request) {
        check();
        String field = request.getParameter("button");
        m.addAttribute("user", user);
        if (field.toLowerCase().contains("with")) {
            return "redirect:withdraw";
        } else if (field.toLowerCase().contains("deposit")) {
            return "redirect:deposit";
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.GET)
    public String withdraw(Model m) {
        check();
        m.addAttribute("user", user);
        return "withdraw";
    }

    @RequestMapping(value = "/loan", method = RequestMethod.GET)

    public String loan(Model m) {
        check();
        m.addAttribute("user", user);
        return "loan";
    }

    @RequestMapping(value = "/loan", method = RequestMethod.POST)

    public String gainLoans(@ModelAttribute("loanAmount") Double d, Model m) {
        check();
        user.setLoans(user.getLoans() + d);
        user.setMoney(user.getMoney() + d);
        if (d > 10000) {
            user.setCreditScore(user.getCreditScore() - 5);
        } else if (d >= 10000 && d < 100000) {
            user.setCreditScore(user.getCreditScore() - 12);
        } else if (d >= 100000 && d < 1000000) {
            user.setCreditScore(user.getCreditScore() - 30);
        } else {
            user.setCreditScore(user.getCreditScore() - 70);
        }
        userService.updateUser(user, d, 3);
        userService.updateUser(user, user.getMoney(), 2);
        return "redirect:bank";
    }

    @RequestMapping(value = "/payloan", method = RequestMethod.GET)
    public String payLoan(Model m) {
        check();
        m.addAttribute("user", user);
        return "payloan";
    }

    public void check() {
        int difference = time.getMinute() - initialMinute;
        if (difference != 0) {
            user.setBillAmount(user.getBillAmount() + difference * initialBill);
            userService.updateUser(user, user.getBillAmount(), 1);
        }
        if (difference != 0 && difference % 4 == 0) {
            user.setMoney(user.getMoney() + user.getJobIncome());
            userService.updateUser(user, user.getMoney(), 4);
        }
    }
}
