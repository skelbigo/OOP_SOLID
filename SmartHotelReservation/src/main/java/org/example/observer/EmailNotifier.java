package org.example.observer;

import org.example.models.Booking;
import org.example.models.Client;

public class EmailNotifier implements BookingObserver {
    @Override
    public void onBookingCreated(Booking booking) {
        Client client = booking.getClient();
        client.receiveMessage("Email to " + client.getEmail() + ": Your booking for " +
                booking.getRoom().getName() + " is confirmed. Total: " + booking.getTotalPrice());
    }

    @Override
    public void onBookingCancelled(Booking booking) {
        Client client = booking.getClient();
        client.receiveMessage("Email to " + client.getEmail() + ": Your booking has been cancelled.");
    }

    @Override
    public void onCheckInApproaching(Booking booking) {
        Client client = booking.getClient();
        client.receiveMessage("Email to " + client.getEmail() + ": Friendly reminder, your check-in is tomorrow at " + booking.getCheckInDate() + "!");
    }
}
