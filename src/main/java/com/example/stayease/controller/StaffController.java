package com.example.stayease.controller;

import com.example.stayease.model.Staff;
import com.example.stayease.model.StaffRole;
import com.example.stayease.service.StaffService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/** SRP: HTTP + view wiring only. DIP: depends on StaffService interface. */
@Controller
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping({"", "/"})
    public String listStaff(Model model) {
        model.addAttribute("staffList", staffService.getAllStaff());
        return "staff";
    }

    @GetMapping("/new")
    public String newStaffForm(Model model) {
        model.addAttribute("staff", new Staff());
        model.addAttribute("roles", StaffRole.values());
        model.addAttribute("formAction", "/staff");
        model.addAttribute("formTitle", "Add Staff");
        return "staff-form";
    }

    @PostMapping
    public String createStaff(@Valid @ModelAttribute("staff") Staff staff, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("roles", StaffRole.values());
            model.addAttribute("formAction", "/staff");
            model.addAttribute("formTitle", "Add Staff");
            return "staff-form";
        }
        staffService.createStaff(staff);
        return "redirect:/staff";
    }

    @GetMapping("/{id}/edit")
    public String editStaffForm(@PathVariable String id, Model model) {
        model.addAttribute("staff", staffService.getStaffById(id));
        model.addAttribute("roles", StaffRole.values());
        model.addAttribute("formAction", "/staff/" + id);
        model.addAttribute("formTitle", "Edit Staff");
        return "staff-form";
    }

    @PostMapping("/{id}")
    public String updateStaff(@PathVariable String id, @Valid @ModelAttribute("staff") Staff staff,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("roles", StaffRole.values());
            model.addAttribute("formAction", "/staff/" + id);
            model.addAttribute("formTitle", "Edit Staff");
            return "staff-form";
        }
        staffService.updateStaff(id, staff);
        return "redirect:/staff";
    }

    @PostMapping("/{id}/delete")
    public String deleteStaff(@PathVariable String id) {
        staffService.deleteStaff(id);
        return "redirect:/staff";
    }
}
