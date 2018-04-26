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
import javax.validation.ValidatorFactory;
//import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.Validator;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Nick-PC
 */
@Controller
@EnableAutoConfiguration

public class CarController {

    @Autowired
    private UserService userService;

    private Authentication auth;

    private User currentUser = new User();

    private Validator validator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Car.class, new CarEditor());
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

    @ModelAttribute("allCars")
    public List<Car> populateCar() {
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car(-1));
        cars.add(new Car(1));
        cars.add(new Car(2));
        cars.add(new Car(3));
        return cars;
    }

    @RequestMapping(value = "/choosecar", method = RequestMethod.GET)

    public String chooseCar(Model m, @ModelAttribute("carOption") Car car) {

        validate();
        List<Car> carList = new ArrayList<>();
        carList.add(new Car(1));
        carList.add(new Car(2));
        carList.add(new Car(3));

        m.addAttribute("carList", carList);

        //System.out.println("we have gotten kind of far");
        return "Choose Car";
    }

    @RequestMapping(value = "/choosecar", method = RequestMethod.POST)
    public String selectCar(@Valid @ModelAttribute("carOption") Car car, BindingResult result, HttpServletRequest request
    ) {
        //ModelAndView mav = new ModelAndView();
        // System.out.println(request.getParameter("total"));
        Car userCar = new Car(Integer.parseInt(request.getParameter("total")));
        //validate();
        //System.out.println("we have gotten this far");

        if (result.hasErrors()) {
            return "Choose Car";

        } else {
            if (currentUser.getBillAmount() != 0) {
                return "redirect:choosehome";
            } else {

                System.out.println(userCar.getTotalBill());

                //  currentUser.setBillAmount(car.getTotalBill());
                userService.updateUser(currentUser, userCar.getTotalBill(), 1);
                // u.setBillAmount(u.getBillAmount() + c.getTotalBill());

                // newView = new ModelAndView(new RedirectView("/choosehome", true));
                return "redirect:choosehome";
            }

        }

    }

}
