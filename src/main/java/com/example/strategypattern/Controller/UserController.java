package com.example.strategypattern.Controller;

import com.example.strategypattern.Model.RegisterUserModel;
import com.example.strategypattern.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // SIMPLE
    // http://localhost:8080/simple_register
    @GetMapping("/simple_register")
    public String simple_register(Model model) {
        model.addAttribute("user", new RegisterUserModel());
        return "simple_register";
    }

    // STRICT
    // http://localhost:8080/strict_register
    @GetMapping("/strict_register")
    public String strict_register(Model model) {
        model.addAttribute("user", new RegisterUserModel());
        return "strict_register";
    }

    // http://localhost:8080/success
    @GetMapping("/success")
    public String success() {
        return "success";
    }


}
