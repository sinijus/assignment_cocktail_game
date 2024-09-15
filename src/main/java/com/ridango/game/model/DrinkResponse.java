package com.ridango.game.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class DrinkResponse implements Serializable {
    private List<Drink> drinks;
}
