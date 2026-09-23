package com.example.stayease.controller;

import com.example.stayease.model.Room;
import com.example.stayease.model.RoomStatus;
import com.example.stayease.model.RoomType;
import com.example.stayease.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * SRP: HTTP + view wiring only. DIP: depends on RoomService interface.
 */
@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping({"", "/"})
    public String listRooms(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        return "rooms";
    }

    @GetMapping("/new")
    public String newRoomForm(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("types", RoomType.values());
        model.addAttribute("statuses", RoomStatus.values());
        model.addAttribute("formAction", "/rooms");
        model.addAttribute("formTitle", "Add Room");
        return "room-form";
    }

    @PostMapping
    public String createRoom(@Valid @ModelAttribute("room") Room room, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("types", RoomType.values());
            model.addAttribute("statuses", RoomStatus.values());
            model.addAttribute("formAction", "/rooms");
            model.addAttribute("formTitle", "Add Room");
            return "room-form";
        }
        roomService.createRoom(room);
        return "redirect:/rooms";
    }

    @GetMapping("/{id}/edit")
    public String editRoomForm(@PathVariable String id, Model model) {
        model.addAttribute("room", roomService.getRoomById(id));
        model.addAttribute("types", RoomType.values());
        model.addAttribute("statuses", RoomStatus.values());
        model.addAttribute("formAction", "/rooms/" + id);
        model.addAttribute("formTitle", "Edit Room");
        return "room-form";
    }

    @PostMapping("/{id}")
    public String updateRoom(@PathVariable String id, @Valid @ModelAttribute("room") Room room,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("types", RoomType.values());
            model.addAttribute("statuses", RoomStatus.values());
            model.addAttribute("formAction", "/rooms/" + id);
            model.addAttribute("formTitle", "Edit Room");
            return "room-form";
        }
        roomService.updateRoom(id, room);
        return "redirect:/rooms";
    }

    @PostMapping("/{id}/delete")
    public String deleteRoom(@PathVariable String id) {
        roomService.deleteRoom(id);
        return "redirect:/rooms";
    }
}
