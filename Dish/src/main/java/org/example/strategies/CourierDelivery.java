package org.example.strategies;

import java.math.BigDecimal;

public class CourierDelivery implements DeliveryStrategy {
    @Override
    public BigDecimal calculateDeliveryCost(BigDecimal subtotal) {
        return new BigDecimal("60.00");
    }

    @Override
    public String getDeliveryName() {
        return "Courier Delivery";
    }
}
