package com.ridango.game.engine;

import org.springframework.stereotype.Component;

@Component
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    public void startCocktailGame() {
        gameService.startGame();
    }
}
