package com.example.stayease.service;

import com.example.stayease.model.Staff;

import java.util.List;

/** ISP: only staff-related operations - nothing about rooms or bookings here. */
public interface StaffService {
    List<Staff> getAllStaff();
    Staff getStaffById(String id);
    Staff createStaff(Staff staff);
    Staff updateStaff(String id, Staff updatedStaff);
    void deleteStaff(String id);
}
