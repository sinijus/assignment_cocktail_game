package com.ridango.game.ui;

import com.ridango.game.engine.GameValue;
import com.ridango.game.engine.HintHandler;
import com.ridango.game.engine.Session;
import com.ridango.game.model.Drink;
import com.ridango.game.model.HighScore;
import com.ridango.game.service.HighScoreService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Scanner;

@Component
public class ConsoleIO {

    @Resource
    private HighScoreService highScoreService;
    private final InputValidator validator = new InputValidator();
    private final HintHandler hint = new HintHandler();

    public void intro() {
        System.out.println(ConsoleText.WELCOME.getMessage());
        System.out.println(ConsoleText.GAME_NAME.getMessage());
    }

    public void validateAge(Scanner scanner) {
        System.out.println(ConsoleText.AGE.getMessage());
        Boolean isAgeResponseValid = validator.validateScannerAgeInput(scanner);
        if (isAgeResponseValid) {
            System.out.println(ConsoleText.VALID_AGE.getMessage());
        } else {
            System.out.println(ConsoleText.NOT_VALID_AGE.getMessage());
            validateAge(scanner);
        }
    }

    public void showHighScore() {
        HighScore highScore = highScoreService.getHighScoreById(GameValue.HIGH_SCORE_ID.getNumber());
        if (highScore.getHighScore() == null) {
            System.out.println(ConsoleText.HIGH_SCORE.getMessage() + 0);
        } else {
            System.out.println(ConsoleText.HIGH_SCORE.getMessage() + highScore.getHighScore());
        }
    }

    public Boolean handleGuessing(Scanner scanner, Session session, Drink drink, StringBuilder stringHint, Boolean provideHint, int turnsLeft) {
        String drinkName = drink.getStrDrink();
        String instructions = drink.getStrInstructions().trim();
        stringHint = handleHint(drink, stringHint, provideHint, turnsLeft, instructions, drinkName);
        return handleAnswer(scanner, session, drink, stringHint, turnsLeft, drinkName);
    }

    public void handleResult(Boolean didDrinkGetGuessed, Session session, String drinkName) {
        highScoreService.handleSaveNewHighScore(GameValue.HIGH_SCORE_ID.getNumber(), session.getSessionScore());
        if (didDrinkGetGuessed) {
            this.guessSuccess(session, drinkName);
        } else {
            this.guessFailed(session, drinkName);
        }
    }

    public void guessSuccess(Session session, String drinkName) {
        System.out.printf(ConsoleText.SUCCESS.getMessage() + "%n", drinkName);
        System.out.println(ConsoleText.SCORE.getMessage() + session.getSessionScore());
    }

    public void guessFailed(Session session, String drinkName) {
        System.out.printf(ConsoleText.FAILURE.getMessage() + "%n", drinkName);
        System.out.println(ConsoleText.SCORE.getMessage() + session.getSessionScore());
    }

    public Boolean startAnotherRound(Scanner scanner) {
        System.out.println(ConsoleText.NEXT_ROUND.getMessage());
        Boolean startNextRound = validator.validateScannerNextRoundInput(scanner);
        if (startNextRound) {
            return true;
        } else {
            this.printOutro();
            return false;
        }
    }

    public void printOutro() {
        System.out.println(ConsoleText.BYE.getMessage());
    }

    public void fetchingDrinkUnsuccessful(String errorMessage) {
        System.out.println(ConsoleText.FETCHING_DRINK_FAILURE.getMessage() + errorMessage);

    }

    private StringBuilder handleHint(Drink drink, StringBuilder stringHint, Boolean provideHint, int turnsLeft, String instructions, String drinkName) {
        if (!provideHint) {
            System.out.println(ConsoleText.QUESTION.getMessage());
            System.out.println(instructions);
        } else {
            stringHint = hint.expandHintByOneLetter(stringHint, drinkName);
            if (hint.isHintNeeded(turnsLeft)) {
                stringHint = hint.expandHintByOneLetter(stringHint, drinkName);
            }
            System.out.println(ConsoleText.TURNS_LEFT.getMessage() + turnsLeft);
            int nrOfExtraHints = hint.validateAmountOfExtraHints(turnsLeft, drinkName);
            switch (nrOfExtraHints) {
                case 3:
                    System.out.println(ConsoleText.MAIN_INGREDIENT.getMessage() + drink.getStrIngredient1());
                case 2:
                    System.out.println(ConsoleText.GLASS.getMessage() + drink.getStrGlass());
                case 1:
                    System.out.println(ConsoleText.CATEGORY.getMessage() + drink.getStrCategory());
                    break;
                case 0:
                    System.out.println(ConsoleText.INDECENT_WORDS.getMessage());
                default:
            }
        }
        System.out.println(ConsoleText.HINT.getMessage() + stringHint);
        return stringHint;
    }

    private Boolean handleAnswer(Scanner scanner, Session session, Drink drink, StringBuilder stringHint, int turnsLeft, String drinkName) {
        String answer = scanner.nextLine();
        if (drinkName.equalsIgnoreCase(answer)) {
            session.addScore(turnsLeft);
            return true;
        } else if (turnsLeft == 1) {
            return false;
        } else {
            return handleGuessing(scanner, session, drink, stringHint, true, turnsLeft - 1);
        }
    }
}
