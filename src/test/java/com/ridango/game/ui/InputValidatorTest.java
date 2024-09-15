package com.ridango.game.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
public class InputValidatorTest {

    private InputValidator inputValidator;

    @BeforeEach
    public void setUp() {
        inputValidator = new InputValidator();
    }

    @Test
    public void testIsValidDrinkingAge_Under18() {
        assertFalse(inputValidator.isValidDrinkingAge(17));
    }

    @Test
    public void testIsValidDrinkingAge_Exactly18() {
        assertTrue(inputValidator.isValidDrinkingAge(18));
    }

    @Test
    public void testIsValidDrinkingAge_Above18AndBelow125() {
        assertTrue(inputValidator.isValidDrinkingAge(30));
    }

    @Test
    public void testIsValidDrinkingAge_Exactly125() {
        assertFalse(inputValidator.isValidDrinkingAge(125));
    }

    @Test
    public void testIsValidDrinkingAge_Above125() {
        assertFalse(inputValidator.isValidDrinkingAge(130));
    }

    @Test
    public void testIsAgeResponseNotValidInteger_EmptyString() {
        assertTrue(inputValidator.isAgeResponseNotValidInteger(""));
    }

    @Test
    public void testIsAgeResponseNotValidInteger_BlankString() {
        assertTrue(inputValidator.isAgeResponseNotValidInteger("   "));
    }

    @Test
    public void testIsAgeResponseNotValidInteger_ValidInteger() {
        assertFalse(inputValidator.isAgeResponseNotValidInteger("25"));
    }

    @Test
    public void testIsAgeResponseNotValidInteger_InvalidInteger_NonNumeric() {
        assertTrue(inputValidator.isAgeResponseNotValidInteger("abc"));
    }

    @Test
    public void testIsAgeResponseNotValidInteger_InvalidInteger_MixedCharacters() {
        assertTrue(inputValidator.isAgeResponseNotValidInteger("12abc34"));
    }
}
