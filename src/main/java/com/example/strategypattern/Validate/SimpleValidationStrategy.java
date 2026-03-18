package com.example.strategypattern.Validate;

import com.example.strategypattern.Exception.ValidationException;

public class SimpleValidationStrategy implements ValidationStrategy{

    // SIMPLE Validate Email
    public String validateEmail(String email){
        if (!email.contains("@")) {
            throw new ValidationException("Email skal indholde '@'");
        }
        return "Godkendt";
    }

    // SIMPLE Validate Password
    public String validatePassword(String password){
        if (password.length() <= 6 ) {
            throw new ValidationException("Password skal mindst være '6' tegn");
        }
        return "Godkendt";
    }

    // SIMPLE Validate Username
    public String validateUsername(String username){
        if (username.length() <= 3) {
            throw new ValidationException("Username skal mindst være '3' tegn");
        }
        return "Godkendt";
    }
}
