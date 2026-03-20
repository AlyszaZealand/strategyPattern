package com.example.strategypattern.Validate;

import com.example.strategypattern.Service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleValidationStrategyTest {

    // Arrange - initializing objects
    // Act - execute code, can be in same line
    // Assert - verify expected result

    // Arrange
    private final SimpleValidationStrategy simple = new SimpleValidationStrategy();

    // Tester at den returner 'null' hvis email validering er korrekt
    @Test
    void testValidateEmail(){
        String email = "abcabc@abc.com";
        String something = simple.validateEmail(email);
        assertNull(something, "Hvis email er korrekt, return null");
    }

    //Tester at den returner fejl-tekst når validering fejler
    @Test
    void testValidateEmailErrorMessage(){
        SimpleValidationStrategy simple = new SimpleValidationStrategy();
        String email = "abcabcabc.com";
        assertEquals("Email skal indeholde '@'",
                simple.validateEmail(email));
    }

    // Tester at den returner 'null' hvis password validering er korrekt
    @Test
    void testValidatePassword(){
        String password = "123456";
        assertNull(simple.validatePassword(password));
    }

    // Tester at den returner fejl-tekst når validering fejler
    @Test
    void testValidatePasswordErrorMessage(){
        assertEquals("Password skal mindst være '6' tegn",
                simple.validatePassword("1234"));
    }

    // Tester at den returner 'null' hvis username validering er korrekt
    @Test
    void testValidateUserName(){
        String username = "abc";
        assertNull(simple.validateUsername(username));
    }

    // Tester at den returner fejl-tekst når username validering fejler
    @Test
    void testValidateUserNameErrorMessage(){
        String  username = "ab";
        assertEquals("Brugernavn skal mindst være '3' tegn",
                simple.validateUsername(username));
    }







}