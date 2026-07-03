package org.example;

public class Pizzeria {
    private final PizzaFactory pizzaFactory;

    public Pizzeria(PizzaFactory pizzaFactory) {
        if (pizzaFactory == null) {
            throw new IllegalArgumentException("Factory cannot be null");
        }
        this.pizzaFactory = pizzaFactory;
    }

    public Pizza orderPizza(PizzaType pizzaType) {
        if (pizzaType == null) {
            throw new IllegalArgumentException("Cannot order a null pizza type");
        }
        return pizzaFactory.createPizza(pizzaType);
    }
}
