package org.example.strategies;

import java.math.BigDecimal;

public class PickupDelivery implements DeliveryStrategy {
    @Override
    public BigDecimal calculateDeliveryCost(BigDecimal subtotal) {
        return BigDecimal.ZERO;
    }

    @Override
    public String getDeliveryName() {
        return "Pickup";
    }
}
