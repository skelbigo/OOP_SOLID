package org.example.models.dishes;

import org.example.models.Dish;

import java.math.BigDecimal;

public class Burger implements Dish {
    @Override
    public String getDescription() {
        return "Classic Beef Burger";
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("150.0");
    }
}
