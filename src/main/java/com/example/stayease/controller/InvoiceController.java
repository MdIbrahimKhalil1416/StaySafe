package com.example.stayease.controller;

import com.example.stayease.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** SRP: HTTP + view wiring only. DIP: depends on InvoiceService interface. */
@Controller
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping({"", "/"})
    public String listInvoices(Model model) {
        model.addAttribute("invoices", invoiceService.getAllInvoices());
        return "invoices";
    }

    @PostMapping("/{id}/pay")
    public String markAsPaid(@PathVariable String id) {
        invoiceService.markAsPaid(id);
        return "redirect:/invoices";
    }
}
