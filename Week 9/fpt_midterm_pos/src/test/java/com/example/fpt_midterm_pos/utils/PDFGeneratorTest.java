package com.example.fpt_midterm_pos.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.example.fpt_midterm_pos.data.model.Customer;
import com.example.fpt_midterm_pos.data.model.Invoice;
import com.example.fpt_midterm_pos.data.model.InvoiceDetail;

class PDFGeneratorTest {

    @Mock
    private SpringTemplateEngine templateEngine;

    @InjectMocks
    private PDFGenerator pdfGenerator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Invoice createInvoice(String customerName, double price, int quantity) {
        InvoiceDetail detail = new InvoiceDetail();
        detail.setPrice(price);
        detail.setQuantity(quantity);

        Customer customer = new Customer();
        customer.setName(customerName);

        Invoice invoice = new Invoice();
        invoice.setCustomer(customer);
        invoice.setInvoiceDetails(Collections.singletonList(detail));

        return invoice;
    }

    @Test
    void shouldGenerateInvoicePDF_withValidInvoice() throws IOException {
        // Arrange
        Invoice invoice = createInvoice("John Doe", 100.0, 2);
        String htmlContent = "<html><body><p>Invoice Content</p></body></html>";
        when(templateEngine.process(eq("invoice-template"), any(Context.class))).thenReturn(htmlContent);

        // Act
        byte[] pdfBytes = pdfGenerator.generateInvoicePDF(invoice);

        // Assert
        assertNotNull(pdfBytes);
        assertTrue(pdfBytes.length > 0);
        verify(templateEngine, times(1)).process(eq("invoice-template"), any(Context.class));
    }

    @Test
    void shouldGenerateInvoicePDF_withEmptyInvoiceDetails() throws IOException {
        // Arrange
        Customer customer = new Customer();
        customer.setName("John Doe");

        Invoice invoice = new Invoice();
        invoice.setCustomer(customer);
        invoice.setInvoiceDetails(Collections.emptyList());

        String htmlContent = "<html><body><p>Invoice Content</p></body></html>";
        when(templateEngine.process(eq("invoice-template"), any(Context.class))).thenReturn(htmlContent);

        // Act
        byte[] pdfBytes = pdfGenerator.generateInvoicePDF(invoice);

        // Assert
        assertNotNull(pdfBytes);
        assertTrue(pdfBytes.length > 0);
        verify(templateEngine, times(1)).process(eq("invoice-template"), any(Context.class));
    }

    @Test
    void shouldGenerateInvoicePDF_withNullInvoice() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> pdfGenerator.generateInvoicePDF(null));
    }

    @Test
    void shouldGenerateInvoicePDF_withInvalidInvoice_NoCustomer() {
        // Arrange
        Invoice invoice = new Invoice();
        invoice.setCustomer(null);
        invoice.setInvoiceDetails(Collections.emptyList());

        // Act & Assert
        IllegalArgumentException thrownException = assertThrows(IllegalArgumentException.class, () -> pdfGenerator.generateInvoicePDF(invoice));
        assertEquals("Customer data is missing in the invoice", thrownException.getMessage());
    }

    @Test
    void shouldGenerateInvoicePDF_withInvalidInvoice_NoInvoiceDetails() {
        // Arrange
        Customer customer = new Customer();
        customer.setName("John Doe");

        Invoice invoice = new Invoice();
        invoice.setCustomer(customer);
        invoice.setInvoiceDetails(null);  // No invoice details

        // Act & Assert
        IllegalArgumentException thrownException = assertThrows(IllegalArgumentException.class, () -> pdfGenerator.generateInvoicePDF(invoice));
        assertEquals("Invoice details are missing", thrownException.getMessage());
    }
}
