package com.example.strategypattern.Service;

import com.example.strategypattern.Exception.ValidationException;
import com.example.strategypattern.Validate.SimpleValidationStrategy;
import com.example.strategypattern.Validate.StrictValidationStrategy;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    public void validate(){

    }

    public void validationChoice(String mode, String email, String password, String username){
        if(mode.equals("simple")){

        }
        if(mode.equals("strict")){

        }
    }

}
