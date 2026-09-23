package com.example.stayease.repository;

import com.example.stayease.model.Room;
import com.example.stayease.model.RoomStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RoomRepository extends MongoRepository<Room, String> {
    List<Room> findByStatus(RoomStatus status);
}
