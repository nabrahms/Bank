/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
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
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import javax.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author Nick-PC
 */
@Controller
@EnableAutoConfiguration
@SessionAttributes("car")
public class CarController {

    @Autowired
    private UserService userService;

    private final Authentication auth;

    private User currentUser;

    private Validator validator;

    public CarController() {
         ValidatorFactory vF = Validation.buildDefaultValidatorFactory();
        validator = vF.getValidator();
        auth = SecurityContextHolder.getContext().getAuthentication();
        Object myUser = (auth != null) ? auth.getPrincipal() : null;

        if (myUser instanceof User) {
            currentUser = (User) myUser;
        }
       
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

    @RequestMapping(value = "/choosecar", method = RequestMethod.GET)

    public String chooseCar(Model m, @ModelAttribute("carOption") Car car) {

        List<Car> carList = new ArrayList<>();
        carList.add(new Car(1));
        carList.add(new Car(2));
        carList.add(new Car(3));
      
        m.addAttribute("carList", carList);
        //System.out.println("we have gotten kind of far");
        return "Choose Car";
    }

    @RequestMapping(value = "/choosecar", method = RequestMethod.POST)
    public String selectCar(@ModelAttribute("carOption") Car car, BindingResult result,
            SessionStatus status) {
        System.out.println("we have gotten this far");

        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        for (ConstraintViolation<Car> violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            // Add JSR-303 errors to BindingResult
            // This allows Spring to display them in view via a FieldError
            result.addError(new FieldError("car", propertyPath, "Invalid " + propertyPath + "(" + message + ")"));
        }
        if (result.hasErrors()) {
            return "Choose Car";
        }

        status.setComplete();
        System.out.println(car.getTotalBill());

        currentUser.setBillAmount(car.getTotalBill());
        userService.updateUser(currentUser);
        // u.setBillAmount(u.getBillAmount() + c.getTotalBill());

        // newView = new ModelAndView(new RedirectView("/choosehome", true));
        return "redirect:/Choose Home.html";
    }

}
