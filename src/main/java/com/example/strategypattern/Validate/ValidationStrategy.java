package com.example.strategypattern.Validate;

public interface ValidationStrategy {

    String validateEmail(String email);
    String validatePassword(String password);
    String validateUsername(String username);

}
