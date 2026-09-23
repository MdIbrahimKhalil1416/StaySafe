package com.example.stayease.service.impl;

import com.example.stayease.exception.ResourceNotFoundException;
import com.example.stayease.model.Booking;
import com.example.stayease.model.Invoice;
import com.example.stayease.repository.InvoiceRepository;
import com.example.stayease.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

/** DIP: depends on InvoiceRepository (abstraction), injected via constructor. */
@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice generateInvoiceForBooking(Booking booking) {
        long nights = ChronoUnit.DAYS.between(booking.getCheckInDate(), booking.getCheckOutDate());
        if (nights <= 0) {
            nights = 1; // guard against same-day bookings
        }
        double price = booking.getPricePerNight() != null ? booking.getPricePerNight() : 0.0;

        Invoice invoice = new Invoice();
        invoice.setBookingId(booking.getId());
        invoice.setGuestName(booking.getGuestName());
        invoice.setRoomNumber(booking.getRoomNumber());
        invoice.setCheckInDate(booking.getCheckInDate());
        invoice.setCheckOutDate(booking.getCheckOutDate());
        invoice.setNights(nights);
        invoice.setPricePerNight(price);
        invoice.setTotalAmount(nights * price);

        return invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice getInvoiceById(String id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + id));
    }

    @Override
    public void markAsPaid(String id) {
        Invoice invoice = getInvoiceById(id);
        invoice.setPaid(true);
        invoiceRepository.save(invoice);
    }
}
