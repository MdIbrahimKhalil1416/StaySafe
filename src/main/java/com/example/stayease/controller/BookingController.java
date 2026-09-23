package com.example.stayease.controller;

import com.example.stayease.exception.InvalidBookingException;
import com.example.stayease.model.Booking;
import com.example.stayease.service.BookingService;
import com.example.stayease.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * SRP: HTTP + view wiring only - the "is this room available" and
 * "flip the room's status" rules live in BookingServiceImpl, not here.
 * DIP: depends on BookingService and RoomService interfaces.
 */
@Controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final RoomService roomService;

    public BookingController(BookingService bookingService, RoomService roomService) {
        this.bookingService = bookingService;
        this.roomService = roomService;
    }

    @GetMapping({"", "/"})
    public String listBookings(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "bookings";
    }

    @GetMapping("/new")
    public String newBookingForm(Model model) {
        model.addAttribute("booking", new Booking());
        model.addAttribute("availableRooms", roomService.getAvailableRooms());
        return "booking-form";
    }

    @PostMapping
    public String createBooking(@Valid @ModelAttribute("booking") Booking booking, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("availableRooms", roomService.getAvailableRooms());
            return "booking-form";
        }
        try {
            bookingService.createBooking(booking);
        } catch (InvalidBookingException e) {
            model.addAttribute("availableRooms", roomService.getAvailableRooms());
            model.addAttribute("errorMessage", e.getMessage());
            return "booking-form";
        }
        return "redirect:/bookings";
    }

    @PostMapping("/{id}/checkout")
    public String checkout(@PathVariable String id) {
        bookingService.checkoutBooking(id);
        return "redirect:/bookings";
    }

    @PostMapping("/{id}/cancel")
    public String cancel(@PathVariable String id) {
        bookingService.cancelBooking(id);
        return "redirect:/bookings";
    }
}
