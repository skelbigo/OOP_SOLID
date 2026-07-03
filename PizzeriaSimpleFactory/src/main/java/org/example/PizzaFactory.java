package org.example;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class PizzaFactory {
    private final Map<PizzaType, Supplier<Pizza>> pizzaRegistry;

    public PizzaFactory() {
        pizzaRegistry = new EnumMap<>(PizzaType.class);
        pizzaRegistry.put(PizzaType.MARGHERITA, MargheritaPizza::new);
        pizzaRegistry.put(PizzaType.FOUR_CHEESE, FourCheesePizza::new);
        pizzaRegistry.put(PizzaType.PEPPERONI, PepperoniPizza::new);
        pizzaRegistry.put(PizzaType.VEGAN, VeganPizza::new);
    }

    public Pizza createPizza(PizzaType pizzaType) {
        if (pizzaType == null) {
            throw new IllegalArgumentException("Pizza type cannot be null");
        }

        Supplier<Pizza> supplier = pizzaRegistry.get(pizzaType);
        if (supplier == null) {
            throw new IllegalArgumentException("Unsupported pizza type: " + pizzaType);
        }
        return supplier.get();
    }
}
