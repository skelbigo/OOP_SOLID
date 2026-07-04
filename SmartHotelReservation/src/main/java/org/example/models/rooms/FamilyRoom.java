package org.example.models.rooms;

import org.example.models.Room;

import java.math.BigDecimal;

public class FamilyRoom implements Room {
    private final BigDecimal PRICE = new BigDecimal("2500.0");

    @Override
    public String getName() {
        return "Family Room";
    }

    @Override
    public BigDecimal getBasePricePerNight() {
        return PRICE;
    }
}
