package com.example.strategypattern.Validate;

import org.springframework.stereotype.Component;

@Component("simple")
public class SimpleValidationStrategy implements ValidationStrategy{

    // SIMPLE Validate Email
    public String validateEmail(String email){
        if (email == null || !email.contains("@")) {
            return "Email skal indeholde '@'";
        }
        return null;
    }

    // SIMPLE Validate Password
    public String validatePassword(String password){
        if (password == null || password.length() < 6) {
            return "Password skal mindst være '6' tegn";
        }
        return null;
    }

    // SIMPLE Validate Username
    public String validateUsername(String username){
        if (username == null || username.length() < 3) {
            return "Brugernavn skal mindst være '3' tegn";
        }
        return null;
    }
}
