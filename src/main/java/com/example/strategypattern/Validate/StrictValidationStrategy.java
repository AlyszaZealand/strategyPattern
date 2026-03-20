package com.example.strategypattern.Validate;

import org.springframework.stereotype.Component;

@Component("strict")
public class StrictValidationStrategy implements ValidationStrategy {

    // STRICT Validate Email
    @Override
    public String validateEmail(String email){
        if (email == null || !email.contains("@") ) {
             return "Email skal indeholde '@'";
        }

        if(!(email.endsWith(".com") || email.endsWith(".org") || email.endsWith(".net"))){
            return "Email skal slutte på '.com', '.org' eller '.net'";
        }
        return null;
    }

    // STRICT Validate Password
    @Override
    public String validatePassword(String password){
        // Først tjekker og giver error besked
        if ((password == null || password.length() < 10)) {
            return "Password skal mindst være '10' tegn";
        }

        // Derefter udvidet error besked hvis password ER 10 lang
        boolean containNumber = password.matches(".*\\d.*");
        boolean containLetter = password.matches(".*[a-zA-Z].*");
        //Hvis containNumber eller containLetter IKKE er opfyldte, returner vi fejl besked.
        if(!containNumber || !containLetter) {
            return "Password skal indeholde både tal og bogstaver";
        }
        return null;
    }

    // STRICT Validate Username
    @Override
    public String validateUsername(String username){
        if (username == null || username.length() < 6) {
            return "Brugernavn skal mindst være '6' tegn";
        }

        if(username.contains(" ")){
            return "Brugernavn må ikke indeholde 'mellemrum'";
        }
        return null;
    }

}
