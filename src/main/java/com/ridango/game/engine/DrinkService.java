package com.ridango.game.engine;

import com.ridango.game.model.Drink;
import com.ridango.game.model.DrinkResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DrinkService {

    private final WebClient webClient;

    public DrinkService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1")
                .build();
    }

    public Mono<Drink> getDrinkForGuessing(Session session) {
        String url = "/random.php";
        return this.webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(DrinkResponse.class)
                .flatMap(response -> {
                    if (response != null && response.getDrinks() != null && !response.getDrinks().isEmpty()) {
                        Integer drinkId = response.getDrinks().get(0).getIdDrink();
                        if (session.isDrinkUnique(drinkId)) {
                            session.addDrinkId(drinkId);
                            return Mono.just(response.getDrinks().get(0));
                        } else {
                            return getDrinkForGuessing(session);
                        }
                    } else {
                        return Mono.empty();
                    }
                })
                .onErrorResume(e -> {
                    System.err.println("Error occurred: " + e.getMessage());
                    return Mono.empty();
                });
    }
}

