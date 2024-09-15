package com.ridango.game.engine;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class HintHandler {
    public StringBuilder expandHintByOneLetter(StringBuilder stringHint, String drinkName) {
        List<Integer> availableIndexes = new ArrayList<>();
        for (int i = 0; i < stringHint.length(); i++) {
            if (stringHint.charAt(i) == '_') {
                availableIndexes.add(i);
            }
        }
        if (availableIndexes.isEmpty()) {
            return stringHint;
        }
        Random random = new Random();
        int randomAvailableIndex = random.nextInt(availableIndexes.size());
        int charPosition = availableIndexes.get(randomAvailableIndex);
        char newHint = drinkName.charAt(charPosition);
        stringHint.setCharAt(charPosition, newHint);
        return stringHint;
    }

    public boolean isHintNeeded(int turnsLeft) {
        return turnsLeft <= 2;
    }

    public int validateAmountOfExtraHints(int turnsLeft, String drinkName) {
        if (turnsLeft <= 3) {
            switch (turnsLeft) {
                case 3:
                    return 1;
                case 2:
                    return 2;
                case 1:
                    return 3;
            }
        } else if (drinkName.contains("*")) {
            return 0;
        }
        return -1;
    }

    public StringBuilder getStringBuilderHint(String drinkName) {
        StringBuilder stringBuilder = new StringBuilder(drinkName.length());
        stringBuilder.append("_".repeat(drinkName.length()));
        for (int i = 0; i < drinkName.length(); i++) {
            if (drinkName.charAt(i) == ' ') {
                stringBuilder.setCharAt(i, ' ');
            }
        }
        return stringBuilder;
    }
}
