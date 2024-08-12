package com.example.fpt_midterm_pos.mapper;

import com.example.fpt_midterm_pos.data.model.Customer;
import com.example.fpt_midterm_pos.data.model.Status;
import com.example.fpt_midterm_pos.dto.CustomerDTO;
import com.example.fpt_midterm_pos.dto.CustomerInvoiceDTO;
import com.example.fpt_midterm_pos.dto.CustomerSaveDTO;
import com.example.fpt_midterm_pos.dto.CustomerShowDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    private CustomerMapper customerMapper;

    @BeforeEach
    void setUp() {
        customerMapper = Mappers.getMapper(CustomerMapper.class);
    }

    private Customer createCustomer() {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setName("John Doe");
        customer.setPhoneNumber("+621234567890");
        customer.setStatus(Status.ACTIVE);
        customer.setCreatedAt(new Date());
        customer.setUpdatedAt(new Date());
        return customer;
    }

    private CustomerInvoiceDTO createCustomerInvoiceDTO() {
        return new CustomerInvoiceDTO(
                UUID.randomUUID(),
                "John Doe");
    }

    @Test
    void shouldMapToCustomerDTO() {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setName("John Doe");

        CustomerDTO customerDTO = customerMapper.toCustomerDTO(customer);

        assertEquals(customer.getId(), customerDTO.getId());
        assertEquals(customer.getName(), customerDTO.getName());
    }

    @Test
    void shouldMapToCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Jane Doe");

        Customer customer = customerMapper.toCustomer(customerDTO);

        assertNull(customer.getCreatedAt());
        assertNull(customer.getUpdatedAt());
        assertEquals(customerDTO.getName(), customer.getName());
    }

    @Test
    void shouldMapToCustomerShowDTO() {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setName("John Doe");

        CustomerShowDTO customerShowDTO = customerMapper.toCustomerShowDTO(customer);

        assertEquals(customer.getId(), customerShowDTO.getId());
        assertEquals(customer.getName(), customerShowDTO.getName());
    }

    @Test
    void shouldMapToCustomerSaveDTO() {
        Customer customer = new Customer();
        customer.setName("John Doe");

        CustomerSaveDTO customerSaveDTO = customerMapper.toCustomerSaveDTO(customer);

        assertEquals(customer.getName(), customerSaveDTO.getName());
    }

    @Test
    void shouldMapToCustomerInvoiceDTO() {
        // Arrange
        Customer customer = createCustomer();

        // Act
        CustomerInvoiceDTO customerInvoiceDTO = customerMapper.toCostumerInvoiceDTO(customer);

        // Assert
        assertEquals(customer.getId(), customerInvoiceDTO.getId());
        assertEquals(customer.getName(), customerInvoiceDTO.getName());
    }

    @Test
    void shouldReturnNullWhenMappingNullInvoiceDTOToCustomerInvoiceDTO() {
        // Act
        CustomerInvoiceDTO customerInvoiceDTO = customerMapper.toCostumerInvoiceDTO(null);

        // Assert
        assertNull(customerInvoiceDTO);
    }

    @Test
    void shouldMapInvoiceDTOToCustomer() {
        // Arrange
        CustomerInvoiceDTO customerInvoiceDTO = createCustomerInvoiceDTO();

        // Act
        Customer customer = customerMapper.toCustomer(customerInvoiceDTO);

        // Assert
        assertNotNull(customer);
        assertEquals(customerInvoiceDTO.getId(), customer.getId());
        assertEquals(customerInvoiceDTO.getName(), customer.getName());
        // phoneNumber, status, createdAt, updatedAt, and invoice are ignored, so we
        // won't check them
    }

    @Test
    void shouldReturnNullWhenMappingNullInvoiceDTOToCustomer() {
        // Act
        Customer customer = customerMapper.toCustomer((CustomerInvoiceDTO) null);

        // Assert
        assertNull(customer);
    }
}
