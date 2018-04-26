/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


/**
 *
 * @author Nick-PC
 */
@Controller
@EnableAutoConfiguration
@SessionAttributes("home")
public class HomeController {

    @Autowired
    private UserService userService;

    private Authentication auth;

    private User currentUser;

    private Validator validator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Home.class, new HomeEditor());
    }

    public void validate() {
        ValidatorFactory vF = Validation.buildDefaultValidatorFactory();
        validator = vF.getValidator();
        auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        System.out.println(userName);
        currentUser = userService.findUserByEmail(userName);

        //currentUser = userService.findUserByEmail(emailList.get(0));
    }

    @RequestMapping(value = "/choosehome", method = RequestMethod.GET)
    public String chooseHome(Model m, @ModelAttribute("homeOption") Home home) {
        validate();
        List<Home> homeList = new ArrayList<>();
        homeList.add(new Home(1));
        homeList.add(new Home(2));
        homeList.add(new Home(3));

        m.addAttribute("homeList", homeList);
        return "Choose Home";
    }

    @RequestMapping(value = "/choosehome", method = RequestMethod.POST)
    public String selectHome(@Valid @ModelAttribute("homeOption") Home home, BindingResult result, HttpServletRequest request) {
        // System.out.println("we have gotten this far");
        // ModelAndView newView = new ModelAndView();
        // validate();

        ///System.out.println(request.getParameter("total"));
        Home userHome = new Home(Integer.parseInt(request.getParameter("total")));
        if (result.hasErrors()) {
            return "Choose Home";
        }
        if (currentUser.getBillAmount() > 7000.0) {
            return "redirect:choosejob";
        } else {

            //System.out.println(userHome.getTotalBill());
            userService.updateUser(currentUser, currentUser.getBillAmount() + userHome.getTotalBill(), 1);
            return "redirect:choosejob";

            // u.setBillAmount(u.getBillAmount() + c.getTotalBill());
            //userService.saveHome(u, h.getChoice());
            // newView = new ModelAndView(new RedirectView("/choosehome", true));
        }
    }

}
