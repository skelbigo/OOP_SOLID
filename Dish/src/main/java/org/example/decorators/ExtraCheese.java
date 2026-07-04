package org.example.decorators;

import org.example.models.Dish;

import java.math.BigDecimal;

public class ExtraCheese extends DishDecorator {

    public ExtraCheese(Dish dish) {
        super(dish);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Extra Cheese";
    }

    @Override
    public BigDecimal getPrice() {
        return super.getPrice().add(new BigDecimal("30.0"));
    }
}
