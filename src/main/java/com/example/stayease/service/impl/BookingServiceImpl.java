package com.example.stayease.service.impl;

import com.example.stayease.exception.InvalidBookingException;
import com.example.stayease.exception.ResourceNotFoundException;
import com.example.stayease.model.Booking;
import com.example.stayease.model.BookingStatus;
import com.example.stayease.model.Room;
import com.example.stayease.model.RoomStatus;
import com.example.stayease.repository.BookingRepository;
import com.example.stayease.service.BookingService;
import com.example.stayease.service.InvoiceService;
import com.example.stayease.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DIP in action: this service depends on RoomService and InvoiceService (INTERFACES),
 * not their impl classes, to flip room availability and generate a bill when a booking
 * is made / ended. If either implementation changes later, nothing here has to change.
 */
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final RoomService roomService;
    private final InvoiceService invoiceService;

    public BookingServiceImpl(BookingRepository bookingRepository, RoomService roomService,
                               InvoiceService invoiceService) {
        this.bookingRepository = bookingRepository;
        this.roomService = roomService;
        this.invoiceService = invoiceService;
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(String id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
    }

    @Override
    public Booking createBooking(Booking booking) {
        Room room = roomService.getRoomById(booking.getRoomId());

        if (room.getStatus() != RoomStatus.AVAILABLE) {
            throw new InvalidBookingException("Room " + room.getRoomNumber() + " is not available");
        }

        booking.setRoomNumber(room.getRoomNumber());
        booking.setPricePerNight(room.getPricePerNight());
        booking.setStatus(BookingStatus.BOOKED);
        Booking saved = bookingRepository.save(booking);

        roomService.updateRoomStatus(room.getId(), RoomStatus.OCCUPIED);
        return saved;
    }

    @Override
    public void checkoutBooking(String id) {
        Booking booking = getBookingById(id);
        booking.setStatus(BookingStatus.CHECKED_OUT);
        bookingRepository.save(booking);
        roomService.updateRoomStatus(booking.getRoomId(), RoomStatus.AVAILABLE);
        invoiceService.generateInvoiceForBooking(booking);
    }

    @Override
    public void cancelBooking(String id) {
        Booking booking = getBookingById(id);
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
        roomService.updateRoomStatus(booking.getRoomId(), RoomStatus.AVAILABLE);
    }

    @Override
    public List<Booking> getActiveBookings() {
        return bookingRepository.findByStatus(BookingStatus.BOOKED);
    }
}
