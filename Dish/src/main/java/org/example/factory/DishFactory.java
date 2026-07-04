package org.example.factory;

import org.example.models.Dish;
import org.example.models.dishes.Burger;
import org.example.models.dishes.Pizza;
import org.example.models.dishes.Salad;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class DishFactory {
    private final Map<DishType, Supplier<Dish>> registry;

    public DishFactory() {
        registry = new EnumMap<>(DishType.class);
        registry.put(DishType.BURGER, Burger::new);
        registry.put(DishType.PIZZA, Pizza::new);
        registry.put(DishType.SALAD, Salad::new);
    }

    public Dish createDish(DishType dishType) {
        if (dishType == null) {
            throw new IllegalArgumentException("Dish type cannot be null");
        }

        Supplier<Dish> supplier = registry.get(dishType);
        if (supplier == null) {
            throw new IllegalArgumentException("Unsupported dish type: " + dishType);
        }
        return supplier.get();
    }
}
