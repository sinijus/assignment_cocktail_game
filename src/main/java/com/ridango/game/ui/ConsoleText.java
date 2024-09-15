package com.ridango.game.ui;

import lombok.Getter;

@Getter
public enum ConsoleText {
    WELCOME("\n\n\nWelcome to the\n"),
    GAME_NAME("""
                /$$$$$$                      /$$         /$$               /$$ /$$        /$$$$$$                                  \s
               /$$__  $$                    | $$        | $$              |__/| $$       /$$__  $$                                 \s
              | $$  \\__/  /$$$$$$   /$$$$$$$| $$   /$$ /$$$$$$    /$$$$$$  /$$| $$      | $$  \\__/  /$$$$$$  /$$$$$$/$$$$   /$$$$$$\s
              | $$       /$$__  $$ /$$_____/| $$  /$$/|_  $$_/   |____  $$| $$| $$      | $$ /$$$$ |____  $$| $$_  $$_  $$ /$$__  $$
               | $$      | $$  \\ $$| $$      | $$$$$$/   | $$      /$$$$$$$| $$| $$      | $$|_  $$  /$$$$$$$| $$ \\ $$ \\ $$| $$$$$$$$
              | $$    $$| $$  | $$| $$      | $$_  $$   | $$ /$$ /$$__  $$| $$| $$      | $$  \\ $$ /$$__  $$| $$ | $$ | $$| $$_____/
              |  $$$$$$/|  $$$$$$/|  $$$$$$$| $$ \\  $$  |  $$$$/|  $$$$$$$| $$| $$      |  $$$$$$/|  $$$$$$$| $$ | $$ | $$|  $$$$$$$
                \\______/  \\______/  \\_______/|__/  \\__/   \\___/   \\_______/|__/|__/       \\______/  \\_______/|__/ |__/ |__/ \\_______/
                                                                                                                                   \s                                                                                                                                                                                                                                \\______/                                                              \s
            """),
    AGE("What is your age?"),
    NOT_VALID_AGE("No way!"),
    VALID_AGE("Good to go!"),
    HIGH_SCORE("High score: "),
    FETCHING_DRINK_FAILURE("Fetching drink unsuccessful, please check your internet connection:\n"),
    QUESTION("What is the drink that has the following instructions:"),
    TURNS_LEFT("Turns left: "),
    MAIN_INGREDIENT("Main ingredient: "),
    GLASS("Glass: "),
    CATEGORY("Category: "),
    INDECENT_WORDS("Letters in names containing indecent words can be marked with '*'"),
    HINT("Hint: "),
    SCORE("Score: "),
    NEXT_ROUND("Up for another round (y/n)?"),
    N("n"),
    Y("y"),
    SUCCESS("You guessed it! It was %s! Congrats!"),
    BYE("Bye!\n"),
    FAILURE("It was %s.");

    private final String message;

    ConsoleText(String message) {
        this.message = message;
    }

}

