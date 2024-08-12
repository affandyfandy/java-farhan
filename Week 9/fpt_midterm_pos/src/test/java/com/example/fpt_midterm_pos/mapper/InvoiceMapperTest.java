package com.example.fpt_midterm_pos.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.fpt_midterm_pos.data.model.Customer;
import com.example.fpt_midterm_pos.data.model.Invoice;
import com.example.fpt_midterm_pos.data.model.InvoiceDetail;
import com.example.fpt_midterm_pos.dto.CustomerInvoiceDTO;
import com.example.fpt_midterm_pos.dto.InvoiceDTO;
import com.example.fpt_midterm_pos.dto.InvoiceSaveDTO;
import com.example.fpt_midterm_pos.dto.InvoiceDetailDTO;
import com.example.fpt_midterm_pos.dto.InvoiceDetailSaveDTO;

class InvoiceMapperTest {

    @Mock
    private InvoiceDetailMapper invoiceDetailMapper;

    @InjectMocks
    private final InvoiceMapper invoiceMapper = Mappers.getMapper(InvoiceMapper.class);

    @BeforeEach
    public void initializeMocks() {
        MockitoAnnotations.openMocks(this);
    }

    private Customer createMockCustomer() {
        Customer customer = mock(Customer.class);
        when(customer.getId()).thenReturn(UUID.randomUUID());
        return customer;
    }

    private Invoice createSampleInvoice() {
        return new Invoice(
                UUID.randomUUID(),
                1000.0,
                new Date(),
                new Date(),
                new Date(),
                createMockCustomer(),
                Arrays.asList(new InvoiceDetail())
        );
    }

    private InvoiceDTO createSampleInvoiceDTO() {
        return new InvoiceDTO(
                UUID.randomUUID(),
                1000.0,
                new Date(),
                new CustomerInvoiceDTO(),
                Arrays.asList(new InvoiceDetailDTO())
        );
    }

    private InvoiceSaveDTO createSampleInvoiceSaveDTO() {
        return new InvoiceSaveDTO(
                UUID.randomUUID(),
                Arrays.asList(new InvoiceDetailSaveDTO())
        );
    }

    @Test
    void shouldMapInvoiceToInvoiceDTO() {
        // Arrange
        Invoice invoice = createSampleInvoice();
        when(invoiceDetailMapper.toInvoiceDetailDTO(invoice.getInvoiceDetails().get(0)))
                .thenReturn(new InvoiceDetailDTO());

        // Act
        InvoiceDTO invoiceDTO = invoiceMapper.toInvoiceDTO(invoice);

        // Assert
        assertEquals(invoice.getId(), invoiceDTO.getId());
        assertEquals(invoice.getAmount(), invoiceDTO.getAmount());
        assertEquals(invoice.getDate(), invoiceDTO.getDate());
        assertEquals(invoice.getCustomer().getId(), invoiceDTO.getCustomer().getId());
        assertEquals(1, invoiceDTO.getInvoiceDetails().size()); // Mocked size
    }

    @Test
    void shouldMapInvoiceDTOToInvoice() {
        // Arrange
        InvoiceDTO invoiceDTO = createSampleInvoiceDTO();
        when(invoiceDetailMapper.toInvoiceDetail(invoiceDTO.getInvoiceDetails().get(0)))
                .thenReturn(new InvoiceDetail());

        // Act
        Invoice invoice = invoiceMapper.toInvoice(invoiceDTO);

        // Assert
        assertEquals(invoiceDTO.getId(), invoice.getId());
        assertNull(invoice.getAmount()); // Ignored field
        assertNull(invoice.getDate()); // Ignored field
    }

    @Test
    void shouldMapInvoiceToInvoiceSaveDTO() {
        // Arrange
        Invoice invoice = createSampleInvoice();
        when(invoiceDetailMapper.toInvoiceDetailSaveDTO(invoice.getInvoiceDetails().get(0)))
                .thenReturn(new InvoiceDetailSaveDTO());

        // Act
        InvoiceSaveDTO invoiceSaveDTO = invoiceMapper.toInvoiceSaveDTO(invoice);

        // Assert
        assertEquals(invoice.getCustomer().getId(), invoiceSaveDTO.getCustomerId());
        assertEquals(1, invoiceSaveDTO.getInvoiceDetails().size()); // Mocked size
    }

    @Test
    void shouldMapInvoiceSaveDTOToInvoice() {
        // Arrange
        InvoiceSaveDTO invoiceSaveDTO = createSampleInvoiceSaveDTO();
        when(invoiceDetailMapper.toInvoiceDetail(invoiceSaveDTO.getInvoiceDetails().get(0)))
                .thenReturn(new InvoiceDetail());

        // Act
        Invoice invoice = invoiceMapper.toInvoice(invoiceSaveDTO);

        // Assert
        assertNull(invoice.getId()); // Ignored field
        assertNull(invoice.getAmount()); // Ignored field
        assertNull(invoice.getDate()); // Ignored field
        assertEquals(invoiceSaveDTO.getCustomerId(), invoice.getCustomer().getId());
        assertEquals(1, invoice.getInvoiceDetails().size()); // Mocked size
    }

    @Test
    void shouldReturnNullWhenMappingNullInvoiceToInvoiceDTO() {
        // Act
        InvoiceDTO invoiceDTO = invoiceMapper.toInvoiceDTO(null);

        // Assert
        assertNull(invoiceDTO);
    }

    @Test
    void shouldReturnNullWhenMappingNullInvoiceDTOToInvoice() {
        // Act
        Invoice invoice = invoiceMapper.toInvoice((InvoiceDTO) null);

        // Assert
        assertNull(invoice);
    }

    @Test
    void shouldReturnNullWhenMappingNullInvoiceToInvoiceSaveDTO() {
        // Act
        InvoiceSaveDTO invoiceSaveDTO = invoiceMapper.toInvoiceSaveDTO(null);

        // Assert
        assertNull(invoiceSaveDTO);
    }

    @Test
    void shouldReturnNullWhenMappingNullInvoiceSaveDTOToInvoice() {
        // Act
        Invoice invoice = invoiceMapper.toInvoice((InvoiceSaveDTO) null);

        // Assert
        assertNull(invoice);
    }
}
