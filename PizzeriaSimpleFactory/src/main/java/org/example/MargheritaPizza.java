package org.example;

import java.math.BigDecimal;
import java.util.List;

public class MargheritaPizza implements Pizza {
    @Override
    public String getName() {
        return "Margherita";
    }

    @Override
    public List<String> getIngredients() {
        return List.of("Tomato Sauce", "Mozzarella", "Basil");
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("150.0");
    }
}
