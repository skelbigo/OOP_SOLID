package org.example;

public class CourierDeliveryStrategy implements DeliveryStrategy {
    private final double basePrice = 50.0;
    private final double pricePerKm = 10.0;

    @Override
    public double calculateCost(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        return basePrice + (pricePerKm * order.getDistanceInKm());
    }

}
