package com.example.stayease.repository;

import com.example.stayease.model.Booking;
import com.example.stayease.model.BookingStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {
    List<Booking> findByStatus(BookingStatus status);
}
