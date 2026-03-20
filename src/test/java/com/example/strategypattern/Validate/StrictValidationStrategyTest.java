package com.example.strategypattern.Validate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StrictValidationStrategyTest {

    // Arrange - initializing objects
    // Act - execute code, can be in same line
    // Assert - verify expected result

    // Arrange
    private final StrictValidationStrategy strict = new StrictValidationStrategy();

    // Tester at den returner 'null' hvis email validering er korrekt
    @Test
    void testValidateEmail(){
        String email = "abcabc@abc.com";
        String something = strict.validateEmail(email);
        assertNull(something, "Hvis email er korrekt, return null");
    }

    //Tester at den returner fejl-tekst når validering fejler
    @Test
    void testValidateEmailErrorMessage(){
        StrictValidationStrategy strict = new StrictValidationStrategy();
        String email = "abcabcabc.com";
        assertEquals("Email skal indeholde '@'",
                strict.validateEmail(email));
    }

    //Tester at den returner fejl-tekst når validering fejler
    @Test
    void testValidateEmailEndsWithErrorMessage(){
        StrictValidationStrategy strict = new StrictValidationStrategy();
        String email = "abca@bcabc.dk";
        assertEquals("Email skal slutte på '.com', '.org' eller '.net'"
                , strict.validateEmail(email));
    }

    // Tester at den returner 'null' hvis password validering er korrekt
    @Test
    void testValidatePassword(){
        String password = "123456789AaAaAa";
        assertNull(strict.validatePassword(password));
    }

    // Tester at den returner fejl-tekst når validering fejler
    @Test
    void testValidatePasswordErrorMessage(){
        assertEquals("Password skal mindst være '10' tegn",
                strict.validatePassword("1234"));
    }

    // Tester at den returner fejl-tekst når validering fejler
    @Test
    void testValidatePasswordContainsLettersAndNumbers() {
        assertEquals("Password skal indeholde både tal og bogstaver",
                strict.validatePassword("12345678910"));
    }

    // Tester at den returner 'null' hvis username validering er korrekt
    @Test
    void testValidateUserName(){
        String username = "abcabc";
        assertNull(strict.validateUsername(username));
    }

    // Tester at den returner fejl-tekst når username validering fejler
    @Test
    void testValidateUserNameErrorMessage(){
        String  username = "ab";
        assertEquals("Brugernavn skal mindst være '10' tegn",
                strict.validateUsername(username));
    }

    // Tester at den returner fejl-tekst når username validering fejler
    @Test
    void testValidateUserNameContainsSpaceErrorMessage(){
        String username = "a bdsdsds";
        assertEquals("Brugernavn må ikke indeholde 'mellemrum'"
                ,strict.validateUsername(username));
    }

}