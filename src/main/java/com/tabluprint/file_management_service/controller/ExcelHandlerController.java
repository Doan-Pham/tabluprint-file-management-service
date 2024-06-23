package com.tabluprint.file_management_service.controller;

import com.tabluprint.file_management_service.service.LoggingService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class ExcelHandlerController {

    @Autowired
    LoggingService loggingService;

    @PostMapping("/export")
    public ResponseEntity<byte[]> exportToExcel(@RequestBody SpreadsheetData spreadsheetData) throws IOException {
        loggingService.log("[FMS] - /export - [1] input: " + spreadsheetData.toString() );
        List<List<String>> data = spreadsheetData.getData();
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Exported Data");

            for (int i = 0; i < data.size(); i++) {
                Row row = sheet.createRow(i);
                List<String> rowData = data.get(i);
                for (int j = 0; j < rowData.size(); j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(rowData.get(j));
                }
            }

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            workbook.write(bos);

            loggingService.log("[FMS] - /export - [2] output: Excel file with size = " + bos.size() + " bytes" );
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=exported_spreadsheet.xlsx");
            headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(bos.toByteArray());
        }
    }
}

