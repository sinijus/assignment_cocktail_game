## This is Jaanus Siniväli's cocktail_game_assignment result:

## General comments
If there wouldn't have been recommended time for the task, then I would have preferred to make API that saves game state after each input to database and communicates with front-end.
In my mind this would have been the optimal solution when using SpringBoot and database, but I went with the console game option as it would have exceeded the recommended time limit in my case.

## Startup
* Start the application by running the CocktailGameApplication file in Intellij IDE.

## Explanation
* Both `GameController` and `GameService` are added to `engine` package as they are used on startup and for running the game and are not part of API functionality.
* HTTP request for random cocktail is made with `DrinkService`
* All cases of input and output is handled by `ConsoleIO` with `InputValidator` in `ui` package. All text output is from `ConsoleText` Enum.
* Empty h2 DB is provided in the project, feel free to use it as you like
* H2 database contains only one table row with `id=1` and `high_score` value.
* To save, update and get the high score there is `HighScoreService`, `HighScoreRepository` and`HighScore` entity that belong to their traditional `service`, `repository` and `model` packages.
* As there is no API functionality then there is no controller package.

## Known Bugs
* If high score exceed `Integer` max value 2147483647 (which is very unlikely) then there will be integer overflow in `Session` class method `addScore` that will result in negative high score.
* If cocktail name is 4 letters long then in 5 turns all the letters will be given as hint. I would count it as a good luck for getting such a short cocktail name.

