package org.example.strategies;

import java.math.BigDecimal;

public interface DeliveryStrategy {
    BigDecimal calculateDeliveryCost(BigDecimal subtotal);
    String getDeliveryName();
}
