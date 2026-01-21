package com.company.supportsystem.services;



import java.util.List;

import org.springframework.stereotype.Service;

import com.company.supportsystem.model.Role;
import com.company.supportsystem.model.TicketResponseMerchant;
import com.company.supportsystem.model.User;
import com.company.supportsystem.repository.TicketResponseMerchantRepo;
import com.company.supportsystem.repository.UserRepo;

@Service
public class MerchantNotificationService {

    private final UserRepo userRepo;
    private final TicketResponseMerchantRepo responseRepo;

    public MerchantNotificationService(
            UserRepo userRepo,
            TicketResponseMerchantRepo responseRepo) {
        this.userRepo = userRepo;
        this.responseRepo = responseRepo;
    }

    public List<TicketResponseMerchant> getNotifications(String merchantUsername) {

        User merchant = userRepo.findByUsername(merchantUsername)
                .orElseThrow(() -> new RuntimeException("Merchant not found"));

        if (merchant.getRole() != Role.MERCHANT) {
            throw new RuntimeException("Only merchant can view notifications");
        }

        return responseRepo.findAllForMerchant(merchant.getUserId());
    }
}
