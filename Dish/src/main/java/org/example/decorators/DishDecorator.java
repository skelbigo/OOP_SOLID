package org.example.decorators;

import org.example.models.Dish;

import java.math.BigDecimal;

public abstract class DishDecorator implements Dish {
    protected final Dish decoratedDish;

    protected DishDecorator(Dish decoratedDish) {
        if (decoratedDish == null) {
            throw new IllegalArgumentException("Dish to decorate cannot be null");
        }
        this.decoratedDish = decoratedDish;
    }

    @Override
    public String getDescription() {
        return decoratedDish.getDescription();
    }

    @Override
    public BigDecimal getPrice() {
        return decoratedDish.getPrice();
    }
}
