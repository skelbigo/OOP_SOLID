package org.example;

public class PostalDeliveryStrategy implements DeliveryStrategy {
    private final double pricePerKg = 15.0;

    @Override
    public double calculateCost(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        return pricePerKg * order.getWeightInKg();
    }
}
