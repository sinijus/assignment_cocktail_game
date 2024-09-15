package com.ridango.game;

import com.ridango.game.engine.GameController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CocktailGameApplication implements CommandLineRunner {

    private final GameController gameController;

    public CocktailGameApplication(GameController gameController) {
        this.gameController = gameController;
    }

    public static void main(String[] args) {
        SpringApplication.run(CocktailGameApplication.class, args);
    }

    @Override
    public void run(String... args) {
        gameController.startCocktailGame();
    }
}


