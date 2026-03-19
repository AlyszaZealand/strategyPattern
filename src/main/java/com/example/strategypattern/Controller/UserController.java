package com.example.strategypattern.Controller;

import com.example.strategypattern.Model.RegisterUserModel;
import com.example.strategypattern.Service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {


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

    @PostMapping("/simple_register/success")
    public String handleSimpleRegister(@ModelAttribute("user") RegisterUserModel registerUser, Model model) {
        userService.validationChoice("simple",registerUser.getEmail(),registerUser.getPassword(),registerUser.getUsername());
        model.addAttribute("username", registerUser.getUsername());
        model.addAttribute("email", registerUser.getEmail());
        return "success";
    }

    // STRICT
    // http://localhost:8080/strict_register
    @GetMapping("/strict_register")
    public String strict_register(Model model) {
        model.addAttribute("user", new RegisterUserModel());
        return "strict_register";
    }

    @PostMapping("/strict_register/success")
    public String handleStrictRegister(@ModelAttribute("user") RegisterUserModel registerUser, Model model) {
        userService.validationChoice("strict",registerUser.getEmail(),registerUser.getPassword(),registerUser.getUsername());
        model.addAttribute("username", registerUser.getUsername());
        model.addAttribute("email", registerUser.getEmail());
        return "success";
    }

    // http://localhost:8080/error
    @GetMapping("/error")
    public String error() {
        return "error";
    }

//-----Muligt videre arbejder til en liste med alle registrerede brugere-----------------------------------------------
//    http://localhost:8080/success
//    @GetMapping("/success")
//    public String success() {
//        return "success";
//    }
//---------------------------------------------------------------------------------------------------------------------

}
