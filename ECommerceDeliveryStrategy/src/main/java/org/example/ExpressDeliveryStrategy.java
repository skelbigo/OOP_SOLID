package org.example;

public class ExpressDeliveryStrategy implements DeliveryStrategy {
    private final double basePrice = 100.0;
    private final double pricePerKm = 20.0;

    @Override
    public double calculateCost(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        return basePrice + (pricePerKm * order.getDistanceInKm());
    }
}
