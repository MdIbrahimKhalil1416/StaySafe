package com.example.stayease.model;

public enum RoomStatus {
    AVAILABLE("Available"),
    OCCUPIED("Occupied"),
    MAINTENANCE("Maintenance");

    private final String label;

    RoomStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
