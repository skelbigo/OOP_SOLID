package org.example.strategies;

import java.math.BigDecimal;

public class ExpressDelivery implements DeliveryStrategy {
    @Override
    public BigDecimal calculateDeliveryCost(BigDecimal subtotal) {
        BigDecimal percentageFee = subtotal.multiply(new BigDecimal("0.10"));
        return new BigDecimal("100.00").add(percentageFee);
    }

    @Override
    public String getDeliveryName() {
        return "Express Delivery";
    }
}
