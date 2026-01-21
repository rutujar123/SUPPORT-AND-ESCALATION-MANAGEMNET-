package com.company.supportsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.company.supportsystem.model.TicketResponseMerchant;

public interface TicketResponseMerchantRepo
        extends JpaRepository<TicketResponseMerchant, Long> {

    // ✅ FIXED QUERY
    @Query(
      "SELECT tr FROM TicketResponseMerchant tr " +
      "WHERE tr.ticket.merchant.userId = :merchantId " +
      "ORDER BY tr.createdAt DESC"
    )
    List<TicketResponseMerchant> findAllForMerchant(
            @Param("merchantId") Long merchantId
    );
}
