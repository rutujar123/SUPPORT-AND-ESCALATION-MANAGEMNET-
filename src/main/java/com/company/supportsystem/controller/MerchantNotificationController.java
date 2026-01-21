package com.company.supportsystem.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.company.supportsystem.model.TicketResponseMerchant;
import com.company.supportsystem.services.MerchantNotificationService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/merchant")
public class MerchantNotificationController {

    private final MerchantNotificationService service;

    public MerchantNotificationController(MerchantNotificationService service) {
        this.service = service;
    }

    @GetMapping("/notifications")
    public List<TicketResponseMerchant> getNotifications(
            @RequestParam String merchantUsername) {

        return service.getNotifications(merchantUsername);
    }
}
