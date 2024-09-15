package com.ridango.game;

import com.ridango.game.engine.DrinkService;
import com.ridango.game.engine.GameService;
import com.ridango.game.engine.GameShutdownService;
import com.ridango.game.engine.Session;
import com.ridango.game.model.Drink;
import com.ridango.game.ui.ConsoleIO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import java.util.Scanner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GameIntegrationTest {

    @Mock
    private ConsoleIO consoleIO;

    @Mock
    private DrinkService drinkService;

    @Mock
    private GameShutdownService gameShutdownService;

    @InjectMocks
    private GameService gameService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGameFlow_SuccessfulGuess() {
        // Arrange
        Drink mockDrink = getMockDrink();
        doNothing().when(consoleIO).intro();
        doNothing().when(consoleIO).showHighScore();
        doAnswer(invocation -> true).when(consoleIO).validateAge(any(Scanner.class));
        when(consoleIO.handleGuessing(any(Scanner.class), any(Session.class), eq(mockDrink), any(StringBuilder.class), anyBoolean(), anyInt()))
                .thenReturn(true);
        when(consoleIO.startAnotherRound(any(Scanner.class)))
                .thenReturn(false); // Then exit
        doNothing().when(consoleIO).handleResult(anyBoolean(), any(Session.class), eq(mockDrink.getStrDrink()));
        doNothing().when(gameShutdownService).shutdownApplication();
        when(drinkService.getDrinkForGuessing(any(Session.class)))
                .thenReturn(Mono.just(mockDrink));

        // Act
        gameService.startGame();

        // Assert
        verify(consoleIO).intro();
        verify(consoleIO).validateAge(any(Scanner.class));
        verify(consoleIO).showHighScore();
        verify(consoleIO).handleResult(eq(true), any(Session.class), eq(mockDrink.getStrDrink()));
        verify(consoleIO, times(1)).startAnotherRound(any(Scanner.class));
        verify(gameShutdownService).shutdownApplication();
    }

    @Test
    public void testGameFlow_FailedGuess() {
        //Arrange
        Drink mockDrink = getMockDrink();
        doNothing().when(consoleIO).intro();
        doNothing().when(consoleIO).showHighScore();
        doAnswer(invocation -> true).when(consoleIO).validateAge(any(Scanner.class));
        when(consoleIO.handleGuessing(any(Scanner.class), any(Session.class), eq(mockDrink), any(StringBuilder.class), anyBoolean(), anyInt()))
                .thenReturn(false);
        when(consoleIO.startAnotherRound(any(Scanner.class)))
                .thenReturn(false);
        doNothing().when(consoleIO).handleResult(anyBoolean(), any(Session.class), eq(mockDrink.getStrDrink()));
        doNothing().when(gameShutdownService).shutdownApplication();
        when(drinkService.getDrinkForGuessing(any(Session.class)))
                .thenReturn(Mono.just(mockDrink));
        //Act
        gameService.startGame();

        // Assert
        verify(consoleIO).intro();
        verify(consoleIO).validateAge(any(Scanner.class));
        verify(consoleIO).showHighScore();
        verify(consoleIO).handleResult(eq(false), any(Session.class), eq(mockDrink.getStrDrink()));
        verify(consoleIO, times(1)).startAnotherRound(any(Scanner.class));
        verify(gameShutdownService).shutdownApplication();
    }

    private Drink getMockDrink() {
        Drink drink = new Drink();
        drink.setStrDrink("CocktailName");
        drink.setStrIngredient1("Rum");
        drink.setStrGlass("Highball Glass");
        drink.setStrCategory("Cocktail");
        return drink;
    }
}