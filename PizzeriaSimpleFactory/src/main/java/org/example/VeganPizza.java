package org.example;

import java.math.BigDecimal;
import java.util.List;

public class VeganPizza implements Pizza {
    @Override
    public String getName() {
        return "Vegan";
    }

    @Override
    public List<String> getIngredients() {
        return List.of("Tomato Sauce", "Mushrooms", "Bell Pepper", "Olives", "Onion");
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("175.0");
    }
}