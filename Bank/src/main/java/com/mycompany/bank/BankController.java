/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
//import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Nick-PC
 */
@Controller
@EnableAutoConfiguration
public class BankController {

    @Autowired
    private UserService userService;

    private LoginController l;

    @RequestMapping(value = "/choosecar", method = RequestMethod.GET)
    public String chooseCar() {

        //System.out.println("we have gotten kind of far");
        return "Choose Car";
    }

    @RequestMapping(value = "/choosecar", method = RequestMethod.POST)
    public String selectCar(Model model, HttpServletRequest request) {
        System.out.println("we have gotten this far");
       // ModelAndView newView = new ModelAndView();
        String carName = request.getParameter("carChoice");
        Car c = new Car(carName);
        User u = l.returnUser();
       // u.setBillAmount(u.getBillAmount() + c.getTotalBill());
        userService.saveCar(u, c.getChoice());
       // newView = new ModelAndView(new RedirectView("/choosehome", true));
        return "redirect:/Choose Home.html";
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
        User u = l.returnUser();
       // u.setBillAmount(u.getBillAmount() + c.getTotalBill());
        //userService.saveCar(u, h.getChoice());
       // newView = new ModelAndView(new RedirectView("/choosehome", true));
        return "redirect:/Choose Job.html";
    }
    
    @RequestMapping(value = "/choosejob", method = RequestMethod.GET)
    public String chooseJob() {
        return "Choose Job";
    }
      @RequestMapping(value = "/choosejob", method = RequestMethod.POST)
    public String selectJob(Model model, HttpServletRequest request) {
        System.out.println("we have gotten this far");
       // ModelAndView newView = new ModelAndView();
        String jobName = request.getParameter("jobChoice");
        Job j = new Job(jobName);
        User u = l.returnUser();
       // u.setBillAmount(u.getBillAmount() + c.getTotalBill());
        //userService.saveCar(u, j.getChoice());
       // newView = new ModelAndView(new RedirectView("/choosehome", true));
        return "redirect:/MainMenu.html";
    }
    
    @RequestMapping(value="/mainMenu", method=RequestMethod.GET)
    public String mainMenu(){
        return "Main Menu";
    }
}
