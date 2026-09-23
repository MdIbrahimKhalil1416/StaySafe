package com.example.stayease.repository;

import com.example.stayease.model.Staff;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StaffRepository extends MongoRepository<Staff, String> {
}
