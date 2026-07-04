package org.example;

import org.example.models.Dish;
import org.example.strategies.DeliveryStrategy;

import java.math.BigDecimal;

public class Order {
    private final Dish finalDish;
    private final DeliveryStrategy deliveryStrategy;

    public Order(Dish finalDish, DeliveryStrategy deliveryStrategy) {
        if (finalDish == null) {
            throw new IllegalArgumentException("Dish cannot be null");
        }
        if (deliveryStrategy == null) {
            throw new IllegalArgumentException("Delivery strategy cannot be null");
        }
        this.finalDish = finalDish;
        this.deliveryStrategy = deliveryStrategy;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal dishPrice = finalDish.getPrice();
        BigDecimal deliveryCost = deliveryStrategy.calculateDeliveryCost(dishPrice);
        return dishPrice.add(deliveryCost);
    }

    public String generateReceipt() {
        BigDecimal dishPrice = finalDish.getPrice();
        BigDecimal deliveryCost = deliveryStrategy.calculateDeliveryCost(dishPrice);

        return String.format("Receipt:\nItem: %s (%.2f UAH)\nDelivery: %s (%.2f UAH)\nTotal: %.2f UAH",
                finalDish.getDescription(), dishPrice, deliveryStrategy.getDeliveryName(), deliveryCost,
                getTotalPrice());
    }
}
