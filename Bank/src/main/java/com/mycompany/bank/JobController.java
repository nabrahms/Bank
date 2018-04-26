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
@SessionAttributes("job")
public class JobController {

    @Autowired
    private UserService userService;

    private Authentication auth;

    private User currentUser;

    private Validator validator;

    public void validate() {
        ValidatorFactory vF = Validation.buildDefaultValidatorFactory();
        validator = vF.getValidator();
        auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        System.out.println(userName);
        currentUser = userService.findUserByEmail(userName);

        //currentUser = userService.findUserByEmail(emailList.get(0));
    }

    @RequestMapping(value = "/choosejob", method = RequestMethod.GET)
    public String chooseJob(Model m, @ModelAttribute("jobChoice") Job job) {
        validate();
        List<Job> jobList = new ArrayList<>();
        jobList.add(new Job(1));
        jobList.add(new Job(2));
        jobList.add(new Job(3));
        jobList.add(new Job(4));
        m.addAttribute("jobList", jobList);
        return "Choose Job";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Car.class, new CarEditor());
    }

    @RequestMapping(value = "/choosejob", method = RequestMethod.POST)
    public String selectJob(@ModelAttribute("jobChoice") Job job, BindingResult result, HttpServletRequest request) {
       // ModelAndView mav = new ModelAndView();
      //  System.out.println("we have gotten this far");
        Job userJob = new Job(Integer.parseInt(request.getParameter("total")));
        if (result.hasErrors()) {
           return "Choose Job";
        }
        if (currentUser.getJobIncome() != 0) {
            return "redirect:mainMenu";
        } else {

            System.out.println(userJob.getJobIncome());

            // currentUser.setMoney(job.getJobIncome());
            userService.updateUser(currentUser, userJob.getJobIncome(), 2);
            return "redirect:mainMenu";
        }

       
    }

}
