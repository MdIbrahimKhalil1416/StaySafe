package com.example.stayease.service.impl;

import com.example.stayease.exception.ResourceNotFoundException;
import com.example.stayease.model.Room;
import com.example.stayease.model.RoomStatus;
import com.example.stayease.repository.RoomRepository;
import com.example.stayease.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * DIP: depends on RoomRepository (abstraction), injected via constructor.
 */
@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomById(String id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + id));
    }

    @Override
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(String id, Room updatedRoom) {
        Room existing = getRoomById(id);
        existing.setRoomNumber(updatedRoom.getRoomNumber());
        existing.setType(updatedRoom.getType());
        existing.setPricePerNight(updatedRoom.getPricePerNight());
        existing.setStatus(updatedRoom.getStatus());
        existing.setDescription(updatedRoom.getDescription());
        return roomRepository.save(existing);
    }

    @Override
    public void deleteRoom(String id) {
        if (!roomRepository.existsById(id)) {
            throw new ResourceNotFoundException("Room not found with id: " + id);
        }
        roomRepository.deleteById(id);
    }

    @Override
    public List<Room> getAvailableRooms() {
        return roomRepository.findByStatus(RoomStatus.AVAILABLE);
    }

    @Override
    public void updateRoomStatus(String id, RoomStatus status) {
        Room room = getRoomById(id);
        room.setStatus(status);
        roomRepository.save(room);
    }

    @Override
    public Map<RoomStatus, Long> getRoomCountsByStatus() {
        Map<RoomStatus, Long> counts = new LinkedHashMap<>();
        for (RoomStatus status : Arrays.asList(RoomStatus.AVAILABLE, RoomStatus.OCCUPIED, RoomStatus.MAINTENANCE)) {
            counts.put(status, (long) roomRepository.findByStatus(status).size());
        }
        return counts;
    }
}
