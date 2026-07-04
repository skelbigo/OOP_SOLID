package org.example.models.dishes;

import org.example.models.Dish;

import java.math.BigDecimal;

public class Pizza implements Dish {
    @Override
    public String getDescription() {
        return "Margherita Pizza";
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("250.0");
    }
}
