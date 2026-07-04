package org.example.decorators;

import org.example.models.Dish;

import java.math.BigDecimal;

public class SpicySauce extends DishDecorator {
    public SpicySauce(Dish dish) {
        super(dish);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Spicy Sauce";
    }

    @Override
    public BigDecimal getPrice() {
        return super.getPrice().add(new BigDecimal("15.0"));
    }
}
