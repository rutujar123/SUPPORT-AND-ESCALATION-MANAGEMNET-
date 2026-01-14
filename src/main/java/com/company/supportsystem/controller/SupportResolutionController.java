package com.company.supportsystem.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.company.supportsystem.services.SupportResolutionService;

@RestController
@RequestMapping("/support/tickets")
public class SupportResolutionController {

    private final SupportResolutionService service;

    public SupportResolutionController(SupportResolutionService service) {
        this.service = service;
    }

    @PostMapping("/{ticketId}/send-to-merchant")
    public String sendToMerchant(
            @PathVariable Long ticketId,
            @RequestParam String supportUsername,
            @RequestParam String message,
            @RequestParam(required = false) MultipartFile attachment) {

        service.sendResolutionToMerchant(
                ticketId, supportUsername, message, attachment);

        return "Resolution sent to merchant successfully";
    }
}

