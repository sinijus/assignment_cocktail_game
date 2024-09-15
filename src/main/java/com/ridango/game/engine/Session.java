package com.ridango.game.engine;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class Session {
    private final Set<Integer> guessedDrinkIds;
    private Integer sessionScore;

    public Session() {
        sessionScore = 0;
        this.guessedDrinkIds = new HashSet<>();
    }

    void addDrinkId(Integer drinkId) {
        guessedDrinkIds.add(drinkId);
    }

    boolean isDrinkUnique(Integer drinkId) {
        return !guessedDrinkIds.contains(drinkId);
    }

    public void addScore(int movesLeftValue) {
        sessionScore += movesLeftValue;
    }
}
