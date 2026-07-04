package org.example.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Booking {
    private final String id;
    private final Client client;
    private final Room room;
    private final LocalDate checkInDate;
    private final int nights;
    private final BigDecimal totalPrice;

    public Booking(Client client, Room room, LocalDate checkInDate, int nights, BigDecimal totalPrice) {
        if (client == null || room == null || checkInDate == null || totalPrice == null) {
            throw new IllegalArgumentException("Booking details cannot be null");
        }
        if (nights <= 0) throw new IllegalArgumentException("Nights must be > 0");
        if (totalPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Total price cannot be negative");
        }
        if (checkInDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Check-in date cannot be in the past");
        }

        this.id = UUID.randomUUID().toString();
        this.client = client;
        this.room = room;
        this.checkInDate = checkInDate;
        this.nights = nights;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public int getNights() {
        return nights;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
