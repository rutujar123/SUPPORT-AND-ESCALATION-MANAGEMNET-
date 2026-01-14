package com.company.supportsystem.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.supportsystem.model.MerchantQuery;
import com.company.supportsystem.repository.MerchantQueryRepository;

@Service
public class MerchantQueryExcelService {

    @Autowired
    private MerchantQueryRepository repository;

    public ByteArrayInputStream exportMerchantQueriesToExcel(
            LocalDateTime fromDate,
            LocalDateTime toDate,
            String status
    ) throws IOException {

        List<MerchantQuery> queries =
                (status != null && !status.isBlank())
                        ? repository.findByCreatedAtBetweenAndStatus(fromDate, toDate, status)
                        : repository.findByCreatedAtBetween(fromDate, toDate);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Merchant Queries");

        String[] headers = {
        	    "Query ID",
        	    "Merchant ID",
        	    "Transaction ID",
        	    "Transaction Ref No",
        	    "MID",
        	    "Transaction Date & Time",
        	    "Transaction Amount",
        	    "Payment Type",
        	    "Customer Name",
        	    "Customer Email",
        	    "Disputed Amount",
        	    "Created At",
        	    "Updated At",
        	    "Status"
        	};


        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        int rowIdx = 1;
        for (MerchantQuery q : queries) {
            Row row = sheet.createRow(rowIdx++);

            row.createCell(0).setCellValue(q.getId());
            row.createCell(1).setCellValue(q.getMerchantId());
            row.createCell(2).setCellValue(q.getTransactionId());
            row.createCell(3).setCellValue(q.getTransactionRefNo());
            row.createCell(4).setCellValue(q.getMid());
            row.createCell(5).setCellValue(
                    q.getTransactionDateAndTime() != null ? q.getTransactionDateAndTime().toString() : ""
            );
            row.createCell(6).setCellValue(q.getTransactionAmount() != null ? q.getTransactionAmount() : 0);
            row.createCell(7).setCellValue(q.getPaymentType());
            row.createCell(8).setCellValue(q.getCustomerName());
            row.createCell(9).setCellValue(
            		q.getCustomerEmail());
            row.createCell(10).setCellValue(
                    q.getDisputedAmount() != null ? q.getDisputedAmount() : 0
            );

            row.createCell(11).setCellValue(
                    q.getCreatedAt() != null ? q.getCreatedAt().toString() : ""
            );

            row.createCell(12).setCellValue(
                    q.getUpdatedAt() != null ? q.getUpdatedAt().toString() : ""
            );

            row.createCell(13).setCellValue(q.getStatus());

        }

        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}
