package com.example.fpt_midterm_pos.utils;

import com.example.fpt_midterm_pos.data.model.Invoice;
import com.example.fpt_midterm_pos.data.model.InvoiceDetail;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public class ExcelGenerator {

    private ExcelGenerator() {
        throw new IllegalStateException("Utility class");
    }
    /**
     * Generates an Excel workbook containing invoice data.
     *
     * @param invoices A list of Invoice objects containing invoice details.
     * @return A workbook containing the invoice data in an Excel sheet
     * This method generates an Excel workbook with a sheet named "Invoices". The workbook contains a header row with column names and subsequent rows containing the invoice details. The columns in the Excel sheet are:
     * - Invoice ID
     * - Customer ID
     * - Customer Name
     * - Amount
     * - Product ID
     * - Product Name
     * - Price
     * - Quantity
     * - Product Amount
     */
    public static Workbook generateInvoiceExcel(List<Invoice> invoices) {
        // Create a workbook
        Workbook workbook = new XSSFWorkbook();

        // Create a sheet
        Sheet sheet = workbook.createSheet("Invoices");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Invoice ID");
        headerRow.createCell(1).setCellValue("Customer ID");
        headerRow.createCell(2).setCellValue("Customer Name");
        headerRow.createCell(3).setCellValue("Amount");
        headerRow.createCell(4).setCellValue("Product ID");
        headerRow.createCell(5).setCellValue("Product Name");
        headerRow.createCell(6).setCellValue("Price");
        headerRow.createCell(7).setCellValue("Quantity");
        headerRow.createCell(8).setCellValue("Product Amount");

        // Populate rows with invoice data
        if (invoices != null && !invoices.isEmpty()) {
            int rowIndex = 1;
            for (Invoice invoice : invoices) {
                for (InvoiceDetail detail : invoice.getInvoiceDetails()) {
                    Row dataRow = sheet.createRow(rowIndex++);
                    dataRow.createCell(0).setCellValue(invoice.getId().toString());
                    dataRow.createCell(1).setCellValue(invoice.getCustomer().getId().toString());
                    dataRow.createCell(2).setCellValue(invoice.getCustomer().getName());
                    dataRow.createCell(3).setCellValue(invoice.getAmount());
                    dataRow.createCell(4).setCellValue(detail.getProduct().getId().toString());
                    dataRow.createCell(5).setCellValue(detail.getProduct().getName());
                    dataRow.createCell(6).setCellValue(detail.getPrice());
                    dataRow.createCell(7).setCellValue(detail.getQuantity());
                    dataRow.createCell(8).setCellValue(detail.getAmount());
                }
            }
        }

        // Ensure the workbook is returned even if no invoices are provided
        return workbook;
    }

}