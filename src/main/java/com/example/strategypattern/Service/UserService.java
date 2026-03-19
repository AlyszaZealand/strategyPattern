package com.example.strategypattern.Service;

import com.example.strategypattern.Validate.ValidationStrategy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService{

    private Map<String, ValidationStrategy> strategies = new HashMap<>();

    public UserService(Map<String, ValidationStrategy> strategies){
        this.strategies = strategies;
    }

    public void validationChoice(String mode, String email, String password, String username){
        ValidationStrategy validationStrategy = strategies.get(mode);

        validationStrategy.validateUsername(username);
        validationStrategy.validateEmail(email);
        validationStrategy.validatePassword(password);
    }

}
