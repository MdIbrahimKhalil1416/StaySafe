package com.example.stayease.service;

import com.example.stayease.model.Room;
import com.example.stayease.model.RoomStatus;

import java.util.List;
import java.util.Map;

/**
 * ISP: only room-related operations - booking logic is NOT here, it lives in BookingService.
 */
public interface RoomService {
    List<Room> getAllRooms();
    Room getRoomById(String id);
    Room createRoom(Room room);
    Room updateRoom(String id, Room updatedRoom);
    void deleteRoom(String id);
    List<Room> getAvailableRooms();
    void updateRoomStatus(String id, RoomStatus status);
    Map<RoomStatus, Long> getRoomCountsByStatus();
}
