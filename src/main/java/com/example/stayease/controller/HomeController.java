package com.example.stayease.controller;

import com.example.stayease.service.BookingService;
import com.example.stayease.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final RoomService roomService;
    private final BookingService bookingService;

    public HomeController(RoomService roomService, BookingService bookingService) {
        this.roomService = roomService;
        this.bookingService = bookingService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("roomCounts", roomService.getRoomCountsByStatus());
        model.addAttribute("activeBookings", bookingService.getActiveBookings());
        return "dashboard";
    }
}
