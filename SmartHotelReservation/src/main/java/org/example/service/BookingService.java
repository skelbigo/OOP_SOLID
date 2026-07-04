package org.example.service;

import org.example.factory.RoomFactory;
import org.example.factory.RoomType;
import org.example.models.Booking;
import org.example.models.Client;
import org.example.models.Room;
import org.example.observer.BookingEventManager;
import org.example.observer.BookingObserver;
import org.example.strategy.PricingStrategy;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookingService {
    private final RoomFactory roomFactory;
    private final BookingEventManager eventManager;

    public BookingService(RoomFactory roomFactory, BookingEventManager eventManager) {
        if (roomFactory == null || eventManager == null) throw new IllegalArgumentException("Dependencies cannot be null");
        this.roomFactory = roomFactory;
        this.eventManager = eventManager;
    }

    public Booking createBooking(Client client, RoomType roomType, int nights, LocalDate checkInDate, PricingStrategy pricingStrategy) {
        if (client == null) {
            throw new IllegalArgumentException("Client cannot be null");
        }
        if (roomType == null) {
            throw new IllegalArgumentException("Room type cannot be null");
        }
        if (checkInDate == null) {
            throw new IllegalArgumentException("Check-in date cannot be null");
        }
        if (pricingStrategy == null) {
            throw new IllegalArgumentException("Pricing strategy cannot be null");
        }
        if (nights <= 0) {
            throw new IllegalArgumentException("Nights must be > 0");
        }

        Room room = roomFactory.createRoom(roomType);
        BigDecimal totalPrice = pricingStrategy.calculatePrice(room.getBasePricePerNight(), nights);
        Booking booking = new Booking(client, room, checkInDate, nights, totalPrice);
        eventManager.notifyCreated(booking);
        return booking;
    }

    public void cancelBooking(Booking booking) {
        if (booking == null) {
            throw new IllegalArgumentException("Booking cannot be null");
        }
        eventManager.notifyCancelled(booking);
    }

    public void remindAboutCheckIn(Booking booking) {
        if (booking == null) {
            throw new IllegalArgumentException("Booking cannot be null");
        }
        eventManager.notifyCheckInApproaching(booking);
    }
}
