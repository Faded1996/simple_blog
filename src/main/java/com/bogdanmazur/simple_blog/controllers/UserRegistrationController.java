package com.bogdanmazur.simple_blog.controllers;

import com.bogdanmazur.simple_blog.dto.UserRegistrationDTO;
import com.bogdanmazur.simple_blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDTO registrationDTO() {
        return new UserRegistrationDTO();
    }  // Thymeleaf gets "user" object from this method + instead of this method we can
       // pass a Model as an arg in showRegistrationPage method

    @GetMapping
    public String showRegistrationPage() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDTO registrationDTO) {
        userService.save(registrationDTO);
        return "redirect:/registration?success";
    }

}
