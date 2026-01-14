package com.company.supportsystem.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.supportsystem.services.MerchantQueryExcelService;

@RestController
@RequestMapping("/merchant-query")
public class MerchantQueryController {

    @Autowired
    private MerchantQueryExcelService excelService;

    @GetMapping("/download-excel")
    public ResponseEntity<InputStreamResource> downloadExcel(

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fromDate,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate toDate,

            @RequestParam(required = false)
            String status

    ) throws IOException {

        LocalDate endDate = (toDate != null) ? toDate : LocalDate.now();
        LocalDate startDate = (fromDate != null) ? fromDate : endDate.minusDays(7);

        ByteArrayInputStream excel =
                excelService.exportMerchantQueriesToExcel(
                        startDate.atStartOfDay(),
                        endDate.atTime(23, 59, 59),
                        status
                );

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=merchant_queries.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(excel));
    }
}