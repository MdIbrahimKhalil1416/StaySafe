package com.example.stayease.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * SRP: pure data holder for an invoice.
 * Generated automatically when a booking is checked out (see BookingServiceImpl).
 */
@Document(collection = "invoices")
public class Invoice {

    @Id
    private String id;

    private String bookingId;
    private String guestName;
    private String roomNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private long nights;
    private double pricePerNight;
    private double totalAmount;
    private LocalDate generatedAt = LocalDate.now();
    private boolean paid = false;

    public Invoice() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public long getNights() {
        return nights;
    }

    public void setNights(long nights) {
        this.nights = nights;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDate generatedAt) {
        this.generatedAt = generatedAt;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
