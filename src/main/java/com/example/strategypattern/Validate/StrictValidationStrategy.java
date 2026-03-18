package com.example.strategypattern.Validate;

import com.example.strategypattern.Exception.ValidationException;

public class StrictValidationStrategy implements ValidationStrategy {


    // STRICT Validate Email
    public String validateEmail(String email){
        if (!email.contains("@") && (!email.contains(".com") || !email.contains(".net") || !email.contains(".org"))) {
            throw new ValidationException("Email skal indholde '@'. Og den skal enten være '.com', '.net' eller '.org'");
        }
        return "Godkendt";
    }

    // STRICT Validate Password
    public String validatePassword(String password){
        if ((password.length() <= 10) && !password.matches("[0-9] && [a-z]")) {
            throw new ValidationException("Password skal mindst være '10' tegn og indeholde både tal + bogstaver");
        }
        return "Godkendt";
    }

    // STRICT Validate Username
    public String validateUsername(String username){
        if (username.length() <= 6 && username.contains(" ")) {
            throw new ValidationException("Username skal mindst være '6' tegn og må ikke indholde 'mellemrum'");
        }
        return "Godkendt";
    }

}
