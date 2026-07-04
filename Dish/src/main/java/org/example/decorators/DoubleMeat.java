package org.example.decorators;

import org.example.models.Dish;

import java.math.BigDecimal;

public class DoubleMeat extends DishDecorator {
    public DoubleMeat(Dish dish) {
        super(dish);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Double Meat";
    }

    @Override
    public BigDecimal getPrice() {
        return super.getPrice().add(new BigDecimal("70.0"));
    }
}
