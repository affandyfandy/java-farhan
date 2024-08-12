package com.example.fpt_midterm_pos.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.example.fpt_midterm_pos.data.model.InvoiceDetail;
import com.example.fpt_midterm_pos.data.model.InvoiceDetailKey;
import com.example.fpt_midterm_pos.dto.InvoiceDetailDTO;
import com.example.fpt_midterm_pos.dto.InvoiceDetailSaveDTO;

public class InvoiceDetailMapperTest {

    private InvoiceDetailMapper invoiceDetailMapper;

    @BeforeEach
    public void setUp() {
        invoiceDetailMapper = Mappers.getMapper(InvoiceDetailMapper.class);
    }

    private InvoiceDetail createInvoiceDetail() {
        InvoiceDetailKey key = new InvoiceDetailKey(UUID.randomUUID(), UUID.randomUUID());
        return new InvoiceDetail(
                key,
                null, // Mock or create Invoice if needed
                null, // Mock or create Product if needed
                "Product Name",
                10,
                100.0,
                1000.0,
                new Date(),
                new Date()
        );
    }

    private InvoiceDetailDTO createInvoiceDetailDTO() {
        return new InvoiceDetailDTO(
                UUID.randomUUID(),
                "Product A",
                10,
                100.0,
                1000.0
        );
    }

    private InvoiceDetailSaveDTO createInvoiceDetailSaveDTO() {
        return new InvoiceDetailSaveDTO(
                UUID.randomUUID(),
                10
        );
    }

    private void assertInvoiceDetailEquals(InvoiceDetail invoiceDetail, InvoiceDetailDTO invoiceDetailDTO) {
        assertEquals(invoiceDetail.getId().getProductId(), invoiceDetailDTO.getProductId());
        assertEquals(invoiceDetail.getProductName(), invoiceDetailDTO.getProductName());
        assertEquals(invoiceDetail.getQuantity(), invoiceDetailDTO.getQuantity());
        assertEquals(invoiceDetail.getPrice(), invoiceDetailDTO.getPrice());
        assertEquals(invoiceDetail.getAmount(), invoiceDetailDTO.getAmount());
    }

    @Test
    void shouldMapInvoiceDetailToInvoiceDetailDTO() {
        // Arrange
        InvoiceDetail invoiceDetail = createInvoiceDetail();

        // Act
        InvoiceDetailDTO invoiceDetailDTO = invoiceDetailMapper.toInvoiceDetailDTO(invoiceDetail);

        // Assert
        assertInvoiceDetailEquals(invoiceDetail, invoiceDetailDTO);
    }

    @Test
    void shouldReturnNullWhenMappingNullInvoiceDetailToInvoiceDetailDTO() {
        // Act
        InvoiceDetailDTO invoiceDetailDTO = invoiceDetailMapper.toInvoiceDetailDTO((InvoiceDetail) null);

        // Assert
        assertNull(invoiceDetailDTO);
    }

    @Test
    void shouldMapInvoiceDetailDTOToInvoiceDetail() {
        // Arrange
        InvoiceDetailDTO invoiceDetailDTO = createInvoiceDetailDTO();

        // Act
        InvoiceDetail invoiceDetail = invoiceDetailMapper.toInvoiceDetail(invoiceDetailDTO);

        // Assert
        assertEquals(invoiceDetailDTO.getProductId(), invoiceDetail.getId().getProductId());
        assertEquals(invoiceDetailDTO.getProductName(), invoiceDetail.getProductName());
        assertEquals(invoiceDetailDTO.getQuantity(), invoiceDetail.getQuantity());
        assertEquals(invoiceDetailDTO.getPrice(), invoiceDetail.getPrice());
        assertEquals(invoiceDetailDTO.getAmount(), invoiceDetail.getAmount());
        // invoice, product, createdAt, and updatedAt are ignored, so we won't check them
    }

    @Test
    void shouldReturnNullWhenMappingNullInvoiceDetailDTOToInvoiceDetail() {
        // Act
        InvoiceDetail invoiceDetail = invoiceDetailMapper.toInvoiceDetail((InvoiceDetailDTO) null);

        // Assert
        assertNull(invoiceDetail);
    }

    @Test
    void shouldMapInvoiceDetailToInvoiceDetailSaveDTO() {
        // Arrange
        InvoiceDetail invoiceDetail = createInvoiceDetail();

        // Act
        InvoiceDetailSaveDTO invoiceDetailSaveDTO = invoiceDetailMapper.toInvoiceDetailSaveDTO(invoiceDetail);

        // Assert
        assertEquals(invoiceDetail.getId().getProductId(), invoiceDetailSaveDTO.getProductId());
        assertEquals(invoiceDetail.getQuantity(), invoiceDetailSaveDTO.getQuantity());
    }

    @Test
    void shouldReturnNullWhenMappingNullInvoiceDetailToInvoiceDetailSaveDTO() {
        // Act
        InvoiceDetailSaveDTO invoiceDetailSaveDTO = invoiceDetailMapper.toInvoiceDetailSaveDTO((InvoiceDetail) null);

        // Assert
        assertNull(invoiceDetailSaveDTO);
    }

    @Test
    void shouldMapInvoiceDetailSaveDTOToInvoiceDetail() {
        // Arrange
        InvoiceDetailSaveDTO invoiceDetailSaveDTO = createInvoiceDetailSaveDTO();

        // Act
        InvoiceDetail invoiceDetail = invoiceDetailMapper.toInvoiceDetail(invoiceDetailSaveDTO);

        // Assert
        assertEquals(invoiceDetailSaveDTO.getProductId(), invoiceDetail.getId().getProductId());
        assertEquals(invoiceDetailSaveDTO.getQuantity(), invoiceDetail.getQuantity());
        // invoice, product, productName, amount, price, createdAt, and updatedAt are ignored, so we won't check them
    }

    @Test
    void shouldReturnNullWhenMappingNullInvoiceDetailSaveDTOToInvoiceDetail() {
        // Act
        InvoiceDetail invoiceDetail = invoiceDetailMapper.toInvoiceDetail((InvoiceDetailSaveDTO) null);

        // Assert
        assertNull(invoiceDetail);
    }

    @Test
    void shouldMapInvoiceDetailSaveDTOToInvoiceDetailKey() {
        // Arrange
        InvoiceDetailSaveDTO invoiceDetailSaveDTO = createInvoiceDetailSaveDTO();

        // Act
        InvoiceDetailKey invoiceDetailKey = invoiceDetailMapper.toInvoiceDetailKey(invoiceDetailSaveDTO);

        // Assert
        assertEquals(invoiceDetailSaveDTO.getProductId(), invoiceDetailKey.getProductId());
    }

    @Test
    void shouldReturnNullWhenMappingNullInvoiceDetailSaveDTOToInvoiceDetailKey() {
        // Act
        InvoiceDetailKey invoiceDetailKey = invoiceDetailMapper.toInvoiceDetailKey((InvoiceDetailSaveDTO) null);

        // Assert
        assertNull(invoiceDetailKey);
    }

    @Test
    void shouldMapInvoiceDetailKeyToInvoiceDetailSaveDTO() {
        // Arrange
        InvoiceDetailKey key = new InvoiceDetailKey(UUID.randomUUID(), UUID.randomUUID());

        // Act
        InvoiceDetailSaveDTO invoiceDetailSaveDTO = invoiceDetailMapper.toInvoiceDetailSaveDTO(key);

        // Assert
        assertEquals(invoiceDetailSaveDTO.getProductId(), key.getProductId());
    }

    @Test
    void shouldReturnNullWhenMappingNullInvoiceDetailKeyToInvoiceDetailSaveDTO() {
        // Act
        InvoiceDetailSaveDTO invoiceDetailSaveDTO = invoiceDetailMapper.toInvoiceDetailSaveDTO((InvoiceDetailKey) null);

        // Assert
        assertNull(invoiceDetailSaveDTO);
    }

    @Test
    void shouldMapInvoiceDetailDTOToInvoiceDetailKey() {
        // Arrange
        InvoiceDetailDTO invoiceDetailDTO = createInvoiceDetailDTO();

        // Act
        InvoiceDetailKey invoiceDetailKey = invoiceDetailMapper.toInvoiceDetailKey(invoiceDetailDTO);

        // Assert
        assertEquals(invoiceDetailDTO.getProductId(), invoiceDetailKey.getProductId());
    }

    @Test
    void shouldReturnNullWhenMappingNullInvoiceDetailDTOToInvoiceDetailKey() {
        // Act
        InvoiceDetailKey invoiceDetailKey = invoiceDetailMapper.toInvoiceDetailKey((InvoiceDetailDTO) null);

        // Assert
        assertNull(invoiceDetailKey);
    }

    @Test
    void shouldMapInvoiceDetailKeyToInvoiceDetailDTO() {
        // Arrange
        InvoiceDetailKey key = new InvoiceDetailKey(UUID.randomUUID(), UUID.randomUUID());

        // Act
        InvoiceDetailDTO invoiceDetailDTO = invoiceDetailMapper.toInvoiceDetailDTO(key);

        // Assert
        assertEquals(invoiceDetailDTO.getProductId(), key.getProductId());
    }

    @Test
    void shouldReturnNullWhenMappingNullInvoiceDetailKeyToInvoiceDetailDTO() {
        // Act
        InvoiceDetailDTO invoiceDetailDTO = invoiceDetailMapper.toInvoiceDetailDTO((InvoiceDetailKey) null);

        // Assert
        assertNull(invoiceDetailDTO);
    }
}
