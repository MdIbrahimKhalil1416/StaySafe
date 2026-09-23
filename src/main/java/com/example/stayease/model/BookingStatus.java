package com.example.stayease.model;

public enum BookingStatus {
    BOOKED("Booked"),
    CHECKED_OUT("Checked Out"),
    CANCELLED("Cancelled");

    private final String label;

    BookingStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
