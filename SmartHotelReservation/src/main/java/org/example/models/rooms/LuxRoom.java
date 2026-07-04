package org.example.models.rooms;

import org.example.models.Room;

import java.math.BigDecimal;

public class LuxRoom implements Room {
    private final BigDecimal PRICE = new BigDecimal("5000.0");

    @Override
    public String getName() {
        return "Lux Room";
    }

    @Override
    public BigDecimal getBasePricePerNight() {
        return PRICE;
    }
}
