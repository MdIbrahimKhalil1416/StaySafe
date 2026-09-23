package com.example.stayease.service;

import com.example.stayease.model.Booking;

import java.util.List;

/**
 * ISP: only booking-related operations - room CRUD is NOT here, it lives in RoomService.
 */
public interface BookingService {
    List<Booking> getAllBookings();
    Booking getBookingById(String id);
    Booking createBooking(Booking booking);
    void checkoutBooking(String id);
    void cancelBooking(String id);
    List<Booking> getActiveBookings();
}
