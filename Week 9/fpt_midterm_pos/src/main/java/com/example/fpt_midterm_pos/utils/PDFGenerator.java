package com.example.fpt_midterm_pos.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.example.fpt_midterm_pos.data.model.Invoice;
import com.itextpdf.html2pdf.HtmlConverter;

@Component
public class PDFGenerator {

    private final SpringTemplateEngine templateEngine;

    @Autowired
    public PDFGenerator(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
        throw new IllegalStateException("Utility class");
    }

    /**
     * Generates a PDF invoice for the given invoice object.
     *
     * @param invoice The invoice object containing all the necessary data to generate the PDF.
     * @return A byte array containing the PDF data.
     * @throws IOException If an error occurs while processing the HTML to PDF.
     */
    public byte[] generateInvoicePDF(Invoice invoice) throws IOException {
        // Create a context
        Context context = new Context();

        if (invoice == null) {
            throw new IllegalArgumentException("Invoice must not be null");
        }
        if (invoice.getCustomer() == null) {
            throw new IllegalArgumentException("Customer data is missing in the invoice");
        }
        if (invoice.getInvoiceDetails() == null) {
            throw new IllegalArgumentException("Invoice details are missing");
        }

        // Binding data to the context
        context.setVariable("invoice", invoice);
        context.setVariable("customer", invoice.getCustomer());
        context.setVariable("invoiceDetails", invoice.getInvoiceDetails());
        context.setVariable("totalAmount", invoice.getInvoiceDetails().stream()
                .mapToDouble(detail -> detail.getQuantity() * detail.getPrice()).sum());

        // Gather the template
        String processedHtml = templateEngine.process("invoice-template", context);

        // Processing all the bytearrays and ready to send
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(processedHtml, stream);
        stream.flush();
        return stream.toByteArray();
    }
}