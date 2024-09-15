package com.ridango.game.ui;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class InputValidator {

    public Boolean validateScannerAgeInput(Scanner scanner) {
        String ageResponse = scanner.next().trim();
        scanner.skip("\n");
        if (isAgeResponseNotValidInteger(ageResponse)) {
            return false;
        }
        return isValidDrinkingAge(Integer.parseInt(ageResponse));
    }

    public Boolean validateScannerNextRoundInput(Scanner scanner) {
        String endRoundAnswer = scanner.next().trim();
        scanner.skip("\n");
        if (ConsoleText.Y.getMessage().equalsIgnoreCase(endRoundAnswer)) {
            return true;
        } else if (ConsoleText.N.getMessage().equalsIgnoreCase(endRoundAnswer)) {
            return false;
        } else {
            return validateScannerNextRoundInput(scanner);
        }
    }

    public boolean isValidDrinkingAge(Integer ageResponse) {
        return ageResponse >= 18 && ageResponse < 125;
    }

    public boolean isAgeResponseNotValidInteger(String ageResponse) {
        if (ageResponse.isEmpty() || ageResponse.isBlank()) {
            return true;
        }
        try {
            Integer.parseInt(ageResponse);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
}
