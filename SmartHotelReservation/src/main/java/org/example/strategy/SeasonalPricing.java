package org.example.strategy;

import java.math.BigDecimal;

public class SeasonalPricing extends BasePricingStrategy {
    @Override
    public BigDecimal calculatePrice(BigDecimal basePrice, int nights) {
        validateInputs(basePrice, nights);
        return basePrice.multiply(BigDecimal.valueOf(nights)).multiply(new BigDecimal("1.20"));
    }
}
