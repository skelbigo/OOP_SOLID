package org.example.observer;

import org.example.models.Booking;

public interface BookingObserver {
    void onBookingCreated(Booking booking);
    void onBookingCancelled(Booking booking);
    void onCheckInApproaching(Booking booking);
}
