package org.example.strategy;

import java.math.BigDecimal;

public abstract class BasePricingStrategy implements PricingStrategy {
    protected void validateInputs(BigDecimal basePrice, int nights) {
        if (basePrice == null) throw new IllegalArgumentException("Base price cannot be null");
        if (basePrice.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Base price cannot be negative");
        if (nights <= 0) throw new IllegalArgumentException("Nights must be > 0");
    }
}
