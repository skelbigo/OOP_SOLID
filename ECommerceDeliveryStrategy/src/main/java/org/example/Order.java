package org.example;

public class Order {
    private final double weightInKg;
    private final double distanceInKm;

    private DeliveryStrategy deliveryStrategy;

    public Order(double weightInKg, double distanceInKm, DeliveryStrategy deliveryStrategy) {
        if (weightInKg <= 0) {
            throw new IllegalArgumentException("The order weight must be greater than 0");
        }
        if (distanceInKm < 0) {
            throw new IllegalArgumentException("A distance cannot be negative");
        }
        if (deliveryStrategy == null) {
            throw new IllegalArgumentException("The delivery strategy cannot be null");
        }
        this.weightInKg = weightInKg;
        this.distanceInKm = distanceInKm;
        this.deliveryStrategy = deliveryStrategy;
    }

    public void setDeliveryStrategy(DeliveryStrategy deliveryStrategy) {
        if (deliveryStrategy == null) {
            throw new IllegalArgumentException("The delivery strategy cannot be null");
        }
        this.deliveryStrategy = deliveryStrategy;
    }

    public double getDeliveryCost() {
        return deliveryStrategy.calculateCost(this);
    }

    public double getDistanceInKm() {
        return distanceInKm;
    }

    public double getWeightInKg() {
        return weightInKg;
    }
}
