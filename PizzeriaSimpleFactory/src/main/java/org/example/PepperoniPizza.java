package org.example;

import java.math.BigDecimal;
import java.util.List;

public class PepperoniPizza implements Pizza {
    @Override
    public String getName() {
        return "Pepperoni";
    }

    @Override
    public List<String> getIngredients() {
        return List.of("Tomato Sauce", "Mozzarella", "Pepperoni");
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("190.0");
    }
}