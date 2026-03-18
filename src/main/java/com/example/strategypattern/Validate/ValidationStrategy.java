package com.example.strategypattern.Validate;

import com.example.strategypattern.Model.RegisterUserModel;

public interface ValidationStrategy {

    String validateEmail(String email);
    String validatePassword(String password);
    String validateUsername(String username);

}
