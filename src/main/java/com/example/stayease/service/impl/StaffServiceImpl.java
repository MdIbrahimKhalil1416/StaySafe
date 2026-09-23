package com.example.stayease.service.impl;

import com.example.stayease.exception.ResourceNotFoundException;
import com.example.stayease.model.Staff;
import com.example.stayease.repository.StaffRepository;
import com.example.stayease.service.StaffService;
import org.springframework.stereotype.Service;

import java.util.List;

/** DIP: depends on StaffRepository (abstraction), injected via constructor. */
@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public Staff getStaffById(String id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));
    }

    @Override
    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public Staff updateStaff(String id, Staff updatedStaff) {
        Staff existing = getStaffById(id);
        existing.setName(updatedStaff.getName());
        existing.setRole(updatedStaff.getRole());
        existing.setPhone(updatedStaff.getPhone());
        existing.setEmail(updatedStaff.getEmail());
        existing.setJoiningDate(updatedStaff.getJoiningDate());
        return staffRepository.save(existing);
    }

    @Override
    public void deleteStaff(String id) {
        if (!staffRepository.existsById(id)) {
            throw new ResourceNotFoundException("Staff not found with id: " + id);
        }
        staffRepository.deleteById(id);
    }
}
