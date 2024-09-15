package com.ridango.game.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
public class SessionTest {

    private Session session;

    @BeforeEach
    public void setUp() {
        session = new Session();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, session.getSessionScore());
        assertTrue(session.getGuessedDrinkIds().isEmpty());
    }

    @Test
    public void testAddDrinkId() {
        session.addDrinkId(1);
        assertTrue(session.getGuessedDrinkIds().contains(1));
        assertEquals(1, session.getGuessedDrinkIds().size());
    }

    @Test
    public void testIsDrinkUnique() {
        assertTrue(session.isDrinkUnique(1));
        session.addDrinkId(1);
        assertFalse(session.isDrinkUnique(1));
    }

    @Test
    public void testAddScore() {
        session.addScore(5);
        assertEquals(5, session.getSessionScore());
        session.addScore(3);
        assertEquals(8, session.getSessionScore());
    }
}
