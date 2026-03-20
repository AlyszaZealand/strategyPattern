package com.example.strategypattern.Service;

import com.example.strategypattern.Exception.ValidationException;
import com.example.strategypattern.Validate.ValidationStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService{

    private final Map<String, ValidationStrategy> strategies;

    public UserService(Map<String, ValidationStrategy> strategies){
        this.strategies = strategies;
    }

    //@Compent("simple") & ("strict") bliver sat ind i String delen af Map<String, ValidationStrategy> strategies.
    public void validationChoice(String mode, String email, String password, String username){
        // Kalder på strategy .get(mode), som vælger strategien. Så mode kan være "simple" eller "strict".
        ValidationStrategy validationStrategy = strategies.get(mode);

        if(validationStrategy == null){
            throw new IllegalArgumentException("Ugyldig validationStrategy valgt");
        }

        List<String> errors = new ArrayList<>();

        // Her har vi allerede valgt 'mode' om Simple eller Strict

        String usernameError = validationStrategy.validateUsername(username);
        if(usernameError != null){
            errors.add(usernameError);
        }

        String emailError = validationStrategy.validateEmail(email);
        if(emailError != null){
            errors.add(emailError);
        }

        String passwordError = validationStrategy.validatePassword(password);
        if(passwordError != null){
            errors.add(passwordError);
        }

        if(!errors.isEmpty()){
            throw new ValidationException(errors);
        }
    }

}
