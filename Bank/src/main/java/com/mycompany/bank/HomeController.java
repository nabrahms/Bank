/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank;

import java.util.ArrayList;
import java.util.List;
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

    private final Authentication auth;

    private User currentUser;

    private final Validator validator;
    
    public HomeController(){
        auth = SecurityContextHolder.getContext().getAuthentication();
        Object myUser = (auth != null) ? auth.getPrincipal() : null;

        if (myUser instanceof User) {
            currentUser = (User) myUser;
        }
        ValidatorFactory vF = Validation.buildDefaultValidatorFactory();
        validator =  vF.getValidator();
    }
    
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Car.class, new CarEditor());
    }

    @ModelAttribute("allCars")
    public List<Car> populateCar() {
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car(-1));
        cars.add(new Car(1));
        cars.add(new Car(2));
        cars.add(new Car(3));
        return cars;
    }
    
    
    
    
     @RequestMapping(value = "/choosehome", method = RequestMethod.GET)
    public String chooseHome() {
        return "Choose Home";
    }

    @RequestMapping(value = "/choosehome", method = RequestMethod.POST)
    public String selectHome(Model model, HttpServletRequest request) {
        // System.out.println("we have gotten this far");
        // ModelAndView newView = new ModelAndView();
        String homeName = request.getParameter("carChoice");
        Home h = new Home(homeName);
        currentUser.setBillAmount(h.getTotalBill());
        userService.updateUser(currentUser);

        // u.setBillAmount(u.getBillAmount() + c.getTotalBill());
        //userService.saveCar(u, h.getChoice());
        // newView = new ModelAndView(new RedirectView("/choosehome", true));
        return "redirect:/Choose Job.html";
    }

}
