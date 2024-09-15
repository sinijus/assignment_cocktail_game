package com.ridango.game.engine;

import com.ridango.game.ui.ConsoleIO;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class GameService {

    private final ConsoleIO consoleIO;
    private final GameShutdownService gameShutdownService;
    private final DrinkService drinkService;
    private final Scanner scanner = new Scanner(System.in);
    private final Session session = new Session();
    private final HintHandler hintHandler = new HintHandler();

    public GameService(ConsoleIO consoleIO, DrinkService drinkService, GameShutdownService gameShutdownService) {
        this.consoleIO = consoleIO;
        this.drinkService = drinkService;
        this.gameShutdownService = gameShutdownService;
    }

    public void startGame() {
        consoleIO.intro();
        consoleIO.validateAge(scanner);
        consoleIO.showHighScore();
        handleGuessRound();
    }

    private void handleGuessRound() {
        drinkService.getDrinkForGuessing(session).subscribe(drink -> {
            StringBuilder stringHint = hintHandler.getStringBuilderHint(drink.getStrDrink());
            Boolean didDrinkGetGuessed = consoleIO.handleGuessing(scanner, session, drink, stringHint, false, GameValue.NR_OF_TURNS.getNumber());
            consoleIO.handleResult(didDrinkGetGuessed, session, drink.getStrDrink());
            Boolean startNextRound = consoleIO.startAnotherRound(scanner);
            this.handleRoundEndChoice(startNextRound);
        }, error -> {
            consoleIO.fetchingDrinkUnsuccessful(error.getMessage());
        });
    }

    private void handleRoundEndChoice(Boolean startNextCycle) {
        if (startNextCycle) {
            handleGuessRound();
        } else {
            gameShutdownService.shutdownApplication();

        }
    }
}
