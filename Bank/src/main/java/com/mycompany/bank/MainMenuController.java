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
import org.springframework.validation.BindingResult;
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

    private Double initialBill;

    private LocalDateTime time = LocalDateTime.now();

    private final int initialMinute = time.getMinute();

    private boolean hasBeenChecked;

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
        check();
        //hasBeenChecked = false;
        m.addAttribute("user", user);
        if (user.getMoney() == 0) {
            user.setMoney(user.getJobIncome());
            userService.updateUser(user, user.getMoney(), 4);
        }
        // initialMinute = time.getMinute();
        // m.addAttribute("minute", initialMinute);
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
        //   check();
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

    @RequestMapping(value = "/loan", method = RequestMethod.GET)

    public String loan(Model m) {
        //check();
        m.addAttribute("user", user);
        return "loan";
    }

    @RequestMapping(value = "/loan", method = RequestMethod.POST)

    public String gainLoans(@ModelAttribute("loanAmount") Double d, Model m) {
        check();
        user.setLoans(user.getLoans() + d);
        user.setMoney(user.getMoney() + d);
        if (d < 10000) {
            user.setCreditScore(user.getCreditScore() - 5);
        } else if (d >= 10000 && d < 100000) {
            user.setCreditScore(user.getCreditScore() - 12);
        } else if (d >= 100000 && d < 1000000) {
            user.setCreditScore(user.getCreditScore() - 30);
        } else {
            user.setCreditScore(user.getCreditScore() - 70);
        }
        int cS = user.getCreditScore();
        userService.updateUser(user, user.getLoans(), 3);
        userService.updateUser(user, user.getMoney(), 4);
        userService.updateUser(user, new Double(cS), 5);
        return "redirect:bank";
    }

    @RequestMapping(value = "/payloan", method = RequestMethod.GET)
    public String payLoan(Model m) {
        check();
        m.addAttribute("user", user);
        return "payloan";
    }

    @RequestMapping(value = "/payloan", method = RequestMethod.POST)
    public String actuallyPayLoan(@ModelAttribute("loanPayback") Double d, Model m) {

        if (d > user.getMoney()) {
            m.addAttribute("errorMessage", "You don't have enough money");
            return "payloan";
        } else if (d > user.getLoans()) {
            m.addAttribute("errorMessage", "You don't owe that much");
            return "payloan";
        } else {
            user.setLoans(user.getLoans() - d);
            user.setMoney(user.getMoney() - d);
            if (d < 10000) {
                user.setCreditScore(user.getCreditScore() + 7);
            } else if (d >= 10000 && d < 100000) {
                user.setCreditScore(user.getCreditScore() + 15);
            } else if (d >= 100000 && d < 1000000) {
                user.setCreditScore(user.getCreditScore() + 40);
            } else {
                user.setCreditScore(user.getCreditScore() + 100);
            }

            userService.updateUser(user, user.getLoans(), 3);
            userService.updateUser(user, user.getMoney(), 4);
            userService.updateUser(user, new Double(user.getCreditScore()), 5);
            return "redirect:bank";
        }
        //return "redirect:bank";

    }

    @RequestMapping(value = "/paybills", method = RequestMethod.GET)
    public String payBills(Model m) {
        check();
        m.addAttribute("user", user);
        return "paybills";
    }

    @RequestMapping(value = "/paybills", method = RequestMethod.POST)
    public String actuallyPayBills(@ModelAttribute("billPayback") Double d, Model m, BindingResult result) {
        check();
        if (result.hasErrors()) {
            if (d > user.getMoney()) {
                m.addAttribute("errorMessage", "You don't have enough money");
                return "paybills";
            } else if (d > user.getBillAmount()) {
                m.addAttribute("errorMessage", "You don't owe that much money");
                return "paybills";
            }
        } else {
            user.setBillAmount(user.getBillAmount() - d);
            user.setMoney(user.getMoney() - d);
            if (d < 10000) {
                user.setCreditScore(user.getCreditScore() + 5);
            } else if (d >= 10000 && d < 100000) {
                user.setCreditScore(user.getCreditScore() + 12);
            } else if (d >= 100000 && d < 1000000) {
                user.setCreditScore(user.getCreditScore() + 30);
            } else {
                user.setCreditScore(user.getCreditScore() + 70);
            }

            userService.updateUser(user, user.getBillAmount(), 1);
            userService.updateUser(user, user.getMoney(), 4);
            userService.updateUser(user, new Double(user.getCreditScore()), 5);
            return "redirect:bank";
        }
        return "redirect:bank";
    }

    public void check() {
       

        int difference = LocalDateTime.now().getMinute() - initialMinute;
        if (difference >= 0 && difference != 0 && difference % 4 != 0) {
            user.setBillAmount(user.getBillAmount() + (difference / 15) * initialBill);
            userService.updateUser(user, user.getBillAmount(), 1);
          
        }
        if (difference != 0 && difference % 4 == 0 ) {
            user.setMoney(user.getMoney() + user.getJobIncome());
            user.setBillAmount(user.getBillAmount() + difference * initialBill + .03 * difference * initialBill);
            user.setCreditScore(user.getCreditScore() - 4);
            userService.updateUser(user, user.getMoney(), 4);
            userService.updateUser(user, user.getBillAmount(), 1);
            userService.updateUser(user, new Double(user.getCreditScore()), 5);
         
        }

    }
}
