package com.example.stayease.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * SRP: pure data holder for a hotel room. No booking logic, no persistence code.
 */
@Document(collection = "rooms")
public class Room {

    @Id
    private String id;

    @NotBlank(message = "Room number is required")
    private String roomNumber;

    @NotNull
    private RoomType type = RoomType.SINGLE;

    @NotNull
    @Positive(message = "Price must be greater than 0")
    private Double pricePerNight;

    @NotNull
    private RoomStatus status = RoomStatus.AVAILABLE;

    private String description;

    public Room() {
    }

    public Room(String roomNumber, RoomType type, Double pricePerNight, RoomStatus status, String description) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.status = status;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
