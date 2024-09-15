package com.ridango.game.engine;

import lombok.Getter;

@Getter
public enum GameValue {
    HIGH_SCORE_ID(1),
    NR_OF_TURNS(5);

    private final int number;

    GameValue(int number) {
        this.number = number;
    }
}

