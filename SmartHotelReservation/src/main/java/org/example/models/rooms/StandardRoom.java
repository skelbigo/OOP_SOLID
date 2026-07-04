package org.example.models.rooms;

import org.example.models.Room;

import java.math.BigDecimal;

public class StandardRoom implements Room {
    private final BigDecimal PRICE = new BigDecimal("1000.0");

    @Override
    public String getName() {
        return "Standard Room";
    }

    @Override
    public BigDecimal getBasePricePerNight() {
        return PRICE;
    }
}
