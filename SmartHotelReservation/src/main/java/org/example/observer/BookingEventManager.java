package org.example.observer;

import org.example.models.Booking;

import java.util.LinkedHashSet;
import java.util.Set;

public class BookingEventManager {
    private final Set<BookingObserver> observers = new LinkedHashSet<>();

    public void subscribe(BookingObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer cannot be null");
        }
        observers.add(observer);
    }

    public void notifyCreated(Booking booking) {
        validateBooking(booking);
        for (BookingObserver observer : observers) {
            observer.onBookingCreated(booking);
        }
    }

    public void notifyCancelled(Booking booking) {
        validateBooking(booking);
        for (BookingObserver observer : observers) observer.onBookingCancelled(booking);
    }

    public void notifyCheckInApproaching(Booking booking) {
        validateBooking(booking);
        for (BookingObserver observer : observers) observer.onCheckInApproaching(booking);
    }

    private void validateBooking(Booking booking) {
        if (booking == null) throw new IllegalArgumentException("Booking cannot be null");
    }
}
