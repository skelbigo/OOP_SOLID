package org.example.models.dishes;

import org.example.models.Dish;

import java.math.BigDecimal;

public class Salad implements Dish {
    @Override
    public String getDescription() {
        return "Caesar Salad";
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("120.0");
    }
}
