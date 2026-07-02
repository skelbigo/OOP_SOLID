package org.example;

public class PickupDeliveryStrategy implements DeliveryStrategy {
    @Override
    public double calculateCost(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        return 0.0;
    }
}
