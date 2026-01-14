package com.company.supportsystem.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.supportsystem.model.MerchantQuery;

@Repository
public interface MerchantQueryRepository
        extends JpaRepository<MerchantQuery, Long> {

    List<MerchantQuery> findByCreatedAtBetween(
            LocalDateTime fromDate,
            LocalDateTime toDate
    );

    List<MerchantQuery> findByCreatedAtBetweenAndStatus(
            LocalDateTime fromDate,
            LocalDateTime toDate,
            String status
    );
}
