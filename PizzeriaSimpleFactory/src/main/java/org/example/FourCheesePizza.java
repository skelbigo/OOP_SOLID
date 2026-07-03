package org.example;

import java.math.BigDecimal;
import java.util.List;

public class FourCheesePizza implements Pizza {
    @Override
    public String getName() {
        return "Four Cheese";
    }

    @Override
    public List<String> getIngredients() {
        return List.of("Mozzarella", "Gorgonzola", "Parmesan", "Emmental");
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("220.0");
    }
}