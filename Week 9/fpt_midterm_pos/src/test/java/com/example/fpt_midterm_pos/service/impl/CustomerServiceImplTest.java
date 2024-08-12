package com.example.fpt_midterm_pos.service.impl;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.fpt_midterm_pos.data.model.Customer;
import com.example.fpt_midterm_pos.data.model.Status;
import com.example.fpt_midterm_pos.data.repository.CustomerRepository;
import com.example.fpt_midterm_pos.dto.CustomerDTO;
import com.example.fpt_midterm_pos.dto.CustomerSaveDTO;
import com.example.fpt_midterm_pos.dto.CustomerShowDTO;
import com.example.fpt_midterm_pos.exception.DuplicateStatusException;
import com.example.fpt_midterm_pos.exception.ResourceNotFoundException;
import com.example.fpt_midterm_pos.mapper.CustomerMapper;

class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    private Pageable pageable;
    private Page<Customer> customerPage;

    private static final String CUSTOMER_NOT_FOUND_MESSAGE = "Customer not found";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindAllActiveCustomers() {
        UUID customerId = UUID.randomUUID();
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName("Test Customer");
        customer.setStatus(Status.ACTIVE);
        customer.setPhoneNumber("+62123456789");
        customer.setCreatedAt(new java.util.Date());
        customer.setUpdatedAt(new java.util.Date());

        CustomerShowDTO customerShowDTO = new CustomerShowDTO(customer.getId(), customer.getName(), customer.getPhoneNumber());

        pageable = PageRequest.of(0, 10);
        customerPage = new PageImpl<>(Collections.singletonList(customer));

        when(customerRepository.findByStatus(Status.ACTIVE, pageable)).thenReturn(customerPage);
        when(customerMapper.toCustomerShowDTO(customer)).thenReturn(customerShowDTO);

        Page<CustomerShowDTO> result = customerServiceImpl.findAllActiveCustomer(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(customerShowDTO, result.getContent().get(0));
    }

    @Test
    void shouldFindCustomerByIdWhenExists() {
        UUID customerId = UUID.randomUUID();
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName("Test Customer");
        customer.setStatus(Status.ACTIVE);
        customer.setPhoneNumber("+62123456789");
        customer.setCreatedAt(new java.util.Date());
        customer.setUpdatedAt(new java.util.Date());

        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));

        Customer result = customerServiceImpl.findById(customer.getId());

        assertNotNull(result);
        assertEquals(customer.getId(), result.getId());
        assertEquals(customer.getName(), result.getName());
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenCustomerDoesNotExist() {
        UUID customerId = UUID.randomUUID();

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            customerServiceImpl.findById(customerId);
        });

        assertEquals(CUSTOMER_NOT_FOUND_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldCreateCustomer() {
        CustomerSaveDTO customerSaveDTO = new CustomerSaveDTO("Test Customer", "+62123456789");
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getPhoneNumber(), Status.ACTIVE);

        when(customerMapper.toCustomer(customerSaveDTO)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.toCustomerDTO(customer)).thenReturn(customerDTO);

        CustomerDTO result = customerServiceImpl.createCustomer(customerSaveDTO);

        verify(customerRepository, times(1)).save(customer);
        assertThat(result).isEqualTo(customerDTO);
    }

    @Test
    void shouldUpdateCustomer() {
        UUID customerId = UUID.randomUUID();
        CustomerSaveDTO customerSaveDTO = new CustomerSaveDTO("Test Customer", "+62123456789");
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName("Old Customer");
        CustomerDTO updatedCustomerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getPhoneNumber(), Status.ACTIVE);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerMapper.toCustomer(customerSaveDTO)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.toCustomerDTO(customer)).thenReturn(updatedCustomerDTO);

        CustomerDTO result = customerServiceImpl.updateCustomer(customerId, customerSaveDTO);

        verify(customerRepository, times(1)).save(customer);
        assertThat(result).isEqualTo(updatedCustomerDTO);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenUpdatingNonExistentCustomer() {
        UUID customerId = UUID.randomUUID();
        CustomerSaveDTO customerSaveDTO = new CustomerSaveDTO("Test Customer", "+62123456789");

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            customerServiceImpl.updateCustomer(customerId, customerSaveDTO);
        });

        assertThat(exception.getMessage()).contains(CUSTOMER_NOT_FOUND_MESSAGE);
    }

    @Test
    void shouldDeactivateCustomerStatus() {
        UUID customerId = UUID.randomUUID();
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setStatus(Status.ACTIVE);
        CustomerDTO updatedCustomerDTO = new CustomerDTO(customerId, "Customer", "+62123456789", Status.DEACTIVATED);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.toCustomerDTO(customer)).thenReturn(updatedCustomerDTO);

        CustomerDTO result = customerServiceImpl.updateCustomerStatus(customerId, Status.DEACTIVATED);

        verify(customerRepository, times(1)).save(customer);
        assertThat(result).isEqualTo(updatedCustomerDTO);
    }

    @Test
    void shouldActivateCustomerStatus() {
        UUID customerId = UUID.randomUUID();
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setStatus(Status.DEACTIVATED);  // Customer starts as DEACTIVATED
        CustomerDTO updatedCustomerDTO = new CustomerDTO(customerId, "Customer", "+62123456789", Status.ACTIVE);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.toCustomerDTO(customer)).thenReturn(updatedCustomerDTO);

        CustomerDTO result = customerServiceImpl.updateCustomerStatus(customerId, Status.ACTIVE);

        verify(customerRepository, times(1)).save(customer);
        assertThat(result).isEqualTo(updatedCustomerDTO);
    }

    @Test
    void shouldThrowDuplicateStatusExceptionWhenActivatingCustomerWithActiveStatus() {
        UUID customerId = UUID.randomUUID();
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setStatus(Status.ACTIVE);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        DuplicateStatusException exception = assertThrows(DuplicateStatusException.class, () -> {
            customerServiceImpl.updateCustomerStatus(customerId, Status.ACTIVE);
        });

        assertThat(exception.getMessage()).contains("Customer status is already ACTIVE");
    }

    @Test
    void shouldThrowDuplicateStatusExceptionWhenDeactivatingCustomerWithDEACTIVATEDStatus() {
        UUID customerId = UUID.randomUUID();
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setStatus(Status.DEACTIVATED);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        DuplicateStatusException exception = assertThrows(DuplicateStatusException.class, () -> {
            customerServiceImpl.updateCustomerStatus(customerId, Status.DEACTIVATED);
        });

        assertThat(exception.getMessage()).contains("Customer status is already DEACTIVATED");
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenActivatingNonExistentCustomer() {
        UUID customerId = UUID.randomUUID();

        // When customer does not exist
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // Expecting ResourceNotFoundException
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            customerServiceImpl.updateCustomerStatus(customerId, Status.ACTIVE);
        });

        assertThat(exception.getMessage()).contains(CUSTOMER_NOT_FOUND_MESSAGE);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenDeactivatingNonExistentCustomer() {
        UUID customerId = UUID.randomUUID();

        // When customer does not exist
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // Expecting ResourceNotFoundException
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            customerServiceImpl.updateCustomerStatus(customerId, Status.DEACTIVATED);
        });

        assertThat(exception.getMessage()).contains(CUSTOMER_NOT_FOUND_MESSAGE);
    }
}
