package org.example.decorators;

import org.example.models.Dish;

public class NoOnion extends DishDecorator {
    public NoOnion(Dish dish) {
        super(dish);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (No Onion)";
    }
}
