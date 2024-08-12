package com.example.fpt_midterm_pos.utils;

import com.example.fpt_midterm_pos.data.model.Customer;
import com.example.fpt_midterm_pos.data.model.Invoice;
import com.example.fpt_midterm_pos.data.model.InvoiceDetail;
import com.example.fpt_midterm_pos.data.model.Product;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ExcelGeneratorTest {

    @Test
    void shouldPrivateConstructor() {
        Constructor<ExcelGenerator> constructor = getPrivateConstructor();
        IllegalStateException thrownException = assertThrows(IllegalStateException.class, () -> invokeConstructor(constructor));
        assertEquals("Utility class", thrownException.getMessage());
    }

    private Constructor<ExcelGenerator> getPrivateConstructor() {
        try {
            Constructor<ExcelGenerator> constructor = ExcelGenerator.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor;
        } catch (NoSuchMethodException | SecurityException e) {
            throw new RuntimeException("Reflection exception occurred", e);
        }
    }

    private void invokeConstructor(Constructor<ExcelGenerator> constructor) throws Throwable {
        try {
            constructor.newInstance();
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    @Test
    void shouldGenerateInvoiceExcel() {

        UUID productId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();
        UUID invoiceId = UUID.randomUUID();

        // Create mock data for testing
        Product product = Mockito.mock(Product.class);
        Mockito.when(product.getId()).thenReturn(productId);
        Mockito.when(product.getName()).thenReturn("Test Product");

        InvoiceDetail detail = Mockito.mock(InvoiceDetail.class);
        Mockito.when(detail.getProduct()).thenReturn(product);
        Mockito.when(detail.getPrice()).thenReturn(10.0);
        Mockito.when(detail.getQuantity()).thenReturn(2);
        Mockito.when(detail.getAmount()).thenReturn(20.0);

        Customer customer = Mockito.mock(Customer.class);
        Mockito.when(customer.getId()).thenReturn(customerId);
        Mockito.when(customer.getName()).thenReturn("John Doe");

        Invoice invoice = Mockito.mock(Invoice.class);
        Mockito.when(invoice.getId()).thenReturn(invoiceId);
        Mockito.when(invoice.getCustomer()).thenReturn(customer);
        Mockito.when(invoice.getAmount()).thenReturn(100.0);
        Mockito.when(invoice.getInvoiceDetails()).thenReturn(Collections.singletonList(detail));

        List<Invoice> invoices = Collections.singletonList(invoice);

        // Call the method under test
        Workbook workbook = ExcelGenerator.generateInvoiceExcel(invoices);

        // Verify the workbook is not null
        assertNotNull(workbook);

        // Verify the workbook structure
        Sheet sheet = workbook.getSheet("Invoices");
        assertNotNull(sheet);
        assertEquals(2, sheet.getPhysicalNumberOfRows()); // Header + 1 data row

        // Verify the header row
        Row headerRow = sheet.getRow(0);
        assertEquals("Invoice ID", headerRow.getCell(0).getStringCellValue());
        assertEquals("Customer ID", headerRow.getCell(1).getStringCellValue());
        assertEquals("Customer Name", headerRow.getCell(2).getStringCellValue());
        assertEquals("Amount", headerRow.getCell(3).getStringCellValue());
        assertEquals("Product ID", headerRow.getCell(4).getStringCellValue());
        assertEquals("Product Name", headerRow.getCell(5).getStringCellValue());
        assertEquals("Price", headerRow.getCell(6).getStringCellValue());
        assertEquals("Quantity", headerRow.getCell(7).getStringCellValue());
        assertEquals("Product Amount", headerRow.getCell(8).getStringCellValue());

        // Verify the data row
        Row dataRow = sheet.getRow(1);
        assertEquals(invoiceId.toString(), dataRow.getCell(0).getStringCellValue());
        assertEquals(customerId.toString(), dataRow.getCell(1).getStringCellValue());
        assertEquals("John Doe", dataRow.getCell(2).getStringCellValue());
        assertEquals(100.0, dataRow.getCell(3).getNumericCellValue());
        assertEquals(productId.toString(), dataRow.getCell(4).getStringCellValue());
        assertEquals("Test Product", dataRow.getCell(5).getStringCellValue());
        assertEquals(10.0, dataRow.getCell(6).getNumericCellValue());
        assertEquals(2.0, dataRow.getCell(7).getNumericCellValue());
        assertEquals(20.0, dataRow.getCell(8).getNumericCellValue());
    }

    @Test
    void shouldGenerateInvoiceExcelWithEmptyList() {
        // Call the method with an empty list
        Workbook workbook = ExcelGenerator.generateInvoiceExcel(Collections.emptyList());

        // Verify the workbook is not null
        assertNotNull(workbook);

        // Verify the workbook structure
        Sheet sheet = workbook.getSheet("Invoices");
        assertNotNull(sheet);
        assertEquals(1, sheet.getPhysicalNumberOfRows()); // Only the header row should be present

        // Verify the header row
        Row headerRow = sheet.getRow(0);
        assertEquals("Invoice ID", headerRow.getCell(0).getStringCellValue());
        assertEquals("Customer ID", headerRow.getCell(1).getStringCellValue());
        assertEquals("Customer Name", headerRow.getCell(2).getStringCellValue());
        assertEquals("Amount", headerRow.getCell(3).getStringCellValue());
        assertEquals("Product ID", headerRow.getCell(4).getStringCellValue());
        assertEquals("Product Name", headerRow.getCell(5).getStringCellValue());
        assertEquals("Price", headerRow.getCell(6).getStringCellValue());
        assertEquals("Quantity", headerRow.getCell(7).getStringCellValue());
        assertEquals("Product Amount", headerRow.getCell(8).getStringCellValue());
    }
}
