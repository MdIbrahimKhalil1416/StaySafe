package com.example.stayease.service;

import com.example.stayease.model.Booking;
import com.example.stayease.model.Invoice;

import java.util.List;

/** ISP: only billing operations - nothing about rooms or bookings management here. */
public interface InvoiceService {
    Invoice generateInvoiceForBooking(Booking booking);
    List<Invoice> getAllInvoices();
    Invoice getInvoiceById(String id);
    void markAsPaid(String id);
}
