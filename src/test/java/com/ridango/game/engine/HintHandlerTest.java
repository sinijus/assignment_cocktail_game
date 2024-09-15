package com.ridango.game.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
public class HintHandlerTest {

    private HintHandler hintHandler;

    @BeforeEach
    public void setUp() {
        hintHandler = new HintHandler();
    }

    @Test
    public void testExpandHintByOneLetter_ValidHint() {
        StringBuilder hint = new StringBuilder("_e__");
        String drinkName = "beer";
        hintHandler.expandHintByOneLetter(hint, drinkName);
        assertTrue(hint.toString().contains("e"), "The hint should contain the letter 'e' from the drinkName.");
    }

    @Test
    public void testExpandHintByOneLetter_OneUnderscore() {
        StringBuilder hint = new StringBuilder("be_r");
        String drinkName = "beer";
        StringBuilder result = hintHandler.expandHintByOneLetter(hint, drinkName);
        assertEquals("beer", result.toString(), "The hint should contain 'beer' as there was one underscore.");
    }

    @Test
    public void testExpandHintByOneLetter_NoUnderscore() {
        StringBuilder hint = new StringBuilder("beer");
        String drinkName = "beer";
        StringBuilder result = hintHandler.expandHintByOneLetter(hint, drinkName);
        assertEquals("beer", result.toString(), "The hint should remain unchanged as there are no underscores.");
    }

    @Test
    public void testIsHintNeeded_LessThanOrEqualToTwoTurns() {
        assertTrue(hintHandler.isHintNeeded(2), "Hint is needed when turns left are 2 or less.");
        assertTrue(hintHandler.isHintNeeded(1), "Hint is needed when turns left are 1.");
    }

    @Test
    public void testIsHintNeeded_MoreThanTwoTurns() {
        assertFalse(hintHandler.isHintNeeded(3), "Hint is not needed when turns left are more than 2.");
        assertFalse(hintHandler.isHintNeeded(4), "Hint is not needed when turns left are 4.");
    }

    @Test
    public void testValidateAmountOfExtraHints() {
        assertEquals(1, hintHandler.validateAmountOfExtraHints(3, "drink"), "Should return 1 hint for 3 turns left.");
        assertEquals(2, hintHandler.validateAmountOfExtraHints(2, "drink"), "Should return 2 hints for 2 turns left.");
        assertEquals(3, hintHandler.validateAmountOfExtraHints(1, "drink"), "Should return 3 hints for 1 turn left.");
        assertEquals(0, hintHandler.validateAmountOfExtraHints(4, "fu*k"), "Should return 0 hints if drinkName contains '*'.");
        assertEquals(-1, hintHandler.validateAmountOfExtraHints(4, "drink"), "Should return -1 if turns left is more than 3 and drinkName does not contain '*'.");
    }
    @Test
    public void testGetStringBuilderHint_Basic() {
        String drinkName = "beer";
        String expected = "____";
        StringBuilder result = hintHandler.getStringBuilderHint(drinkName);
        assertEquals(expected, result.toString(), "The hint should be '____' for the drink name 'beer'.");
    }

    @Test
    public void testGetStringBuilderHint_WithSpaces() {
        String drinkName = "tequila sun";
        String expected = "_______ ___";
        StringBuilder result = hintHandler.getStringBuilderHint(drinkName);
        assertEquals(expected, result.toString(), "The hint should be '_______ ___' for the drink name 'tequila sun'.");
    }

    @Test
    public void testGetStringBuilderHint_EmptyString() {
        String drinkName = "";
        String expected = "";
        StringBuilder result = hintHandler.getStringBuilderHint(drinkName);
        assertEquals(expected, result.toString(), "The hint should be an empty string for an empty drink name.");
    }

    @Test
    public void testGetStringBuilderHint_SingleCharacter() {
        String drinkName = "a";
        String expected = "_";
        StringBuilder result = hintHandler.getStringBuilderHint(drinkName);
        assertEquals(expected, result.toString(), "The hint should be '_' for a single character drink name 'a'.");
    }

    @Test
    public void testGetStringBuilderHint_SingleSpace() {
        String drinkName = " ";
        String expected = " ";
        StringBuilder result = hintHandler.getStringBuilderHint(drinkName);
        assertEquals(expected, result.toString(), "The hint should be ' ' for a single space drink name.");
    }
}
