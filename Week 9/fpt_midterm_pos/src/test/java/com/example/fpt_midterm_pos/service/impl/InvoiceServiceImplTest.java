package com.example.fpt_midterm_pos.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.fpt_midterm_pos.data.model.*;
import com.example.fpt_midterm_pos.data.repository.*;
import com.example.fpt_midterm_pos.dto.*;
import com.example.fpt_midterm_pos.exception.BadRequestException;
import com.example.fpt_midterm_pos.exception.ResourceNotFoundException;
import com.example.fpt_midterm_pos.mapper.InvoiceMapper;
import com.example.fpt_midterm_pos.utils.PDFGenerator;
import com.example.fpt_midterm_pos.utils.ExcelGenerator;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

class InvoiceServiceImplTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private InvoiceDetailRepository invoiceDetailRepository;

    @Mock
    private InvoiceMapper invoiceMapper;

    @Mock
    private PDFGenerator pdfGenerator;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    private Invoice invoice;
    private InvoiceDTO invoiceDTO;
    private InvoiceSaveDTO invoiceSaveDTO;
    private Customer customer;
    private Product product;
    private InvoiceDetailSaveDTO invoiceDetailSaveDTO;
    private InvoiceDetail invoiceDetail;

    private MockedStatic<ExcelGenerator> mockedExcelGenerator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockedExcelGenerator = Mockito.mockStatic(ExcelGenerator.class);

        customer = new Customer();
        customer.setId(UUID.randomUUID());

        product = new Product();
        product.setId(UUID.randomUUID());
        product.setQuantity(100);
        product.setPrice(50.0);
        product.setStatus(Status.ACTIVE);

        invoiceDetailSaveDTO = new InvoiceDetailSaveDTO();
        invoiceDetailSaveDTO.setProductId(product.getId());
        invoiceDetailSaveDTO.setQuantity(2);

        invoiceSaveDTO = new InvoiceSaveDTO();
        invoiceSaveDTO.setCustomerId(customer.getId());
        invoiceSaveDTO.setInvoiceDetails(List.of(invoiceDetailSaveDTO));

        invoice = new Invoice();
        invoice.setId(UUID.randomUUID());
        invoice.setCustomer(customer);
        invoice.setAmount(100.0);
        invoice.setCreatedAt(new Date());
        invoice.setUpdatedAt(new Date());

        invoiceDetail = new InvoiceDetail();
        invoiceDetail.setProduct(product);
        invoiceDetail.setQuantity(2);
        invoiceDetail.setAmount(100.0);

        invoiceDTO = new InvoiceDTO();
        invoiceDTO.setId(invoice.getId());
        invoiceDTO.setAmount(invoice.getAmount());
    }

    @AfterEach
    void tearDown() {
        // Close the static mock
        mockedExcelGenerator.close();
    }

    @Test
    @Transactional
    void shouldCreateAndReturnInvoiceDTO() {
        when(customerRepository.findById(any(UUID.class))).thenReturn(Optional.of(customer));
        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.of(product));
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);
        when(invoiceMapper.toInvoiceDTO(any(Invoice.class))).thenReturn(invoiceDTO);
        when(invoiceDetailRepository.saveAll(anyList())).thenReturn(List.of(invoiceDetail));
        InvoiceDTO result = invoiceService.createInvoice(invoiceSaveDTO);

        assertNotNull(result);
        assertEquals(invoiceDTO.getId(), result.getId());
        verify(invoiceRepository, times(2)).save(any(Invoice.class));
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {
        when(customerRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> invoiceService.createInvoice(invoiceSaveDTO));
    }


    @Test
    @Transactional
    void shouldUpdateInvoiceSuccessful() {
        // Arrange
        UUID customerId = UUID.randomUUID();
        UUID invoiceId = UUID.randomUUID();
        UUID invoiceDTOId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();

        Customer testCustomer = new Customer();
        InvoiceSaveDTO testInvoiceSaveDTO = new InvoiceSaveDTO();
        InvoiceDetailSaveDTO testDetailDTO = new InvoiceDetailSaveDTO();
        testDetailDTO.setProductId(productId);
        testDetailDTO.setQuantity(5);

        testInvoiceSaveDTO.setInvoiceDetails(Collections.singletonList(testDetailDTO));
        testInvoiceSaveDTO.setCustomerId(customerId);

        Invoice existingInvoice = new Invoice();
        existingInvoice.setId(invoiceId);
        existingInvoice.setCreatedAt(Date.from(Instant.now().minus(Duration.ofMinutes(5))));
        existingInvoice.setInvoiceDetails(new ArrayList<>());

        Invoice updatedInvoice = new Invoice();
        InvoiceDTO testInvoiceDTO = new InvoiceDTO();
        testInvoiceDTO.setId(invoiceDTOId);

        Product availableProduct = new Product();
        availableProduct.setQuantity(10);
        availableProduct.setStatus(Status.ACTIVE);
        availableProduct.setPrice(100.0);

        // Mock repository responses
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(existingInvoice));
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(updatedInvoice);
        when(productRepository.findById(productId)).thenReturn(Optional.of(availableProduct));
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(testCustomer));
        when(invoiceMapper.toInvoiceDTO(any(Invoice.class))).thenReturn(testInvoiceDTO);
        when(invoiceDetailRepository.saveAll(anyList())).thenReturn(List.of(new InvoiceDetail()));

        // Act
        InvoiceDTO result = invoiceService.updateInvoice(invoiceId, testInvoiceSaveDTO);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(invoiceDTOId);
        verify(invoiceRepository, times(1)).save(any(Invoice.class));
    }


    @Test
    void shouldThrowExceptionWhenInvoiceNotEditable() {
        invoice.setCreatedAt(new Date(System.currentTimeMillis() - 11 * 60 * 1000)); // Set to 11 minutes ago
        when(invoiceRepository.findById(any(UUID.class))).thenReturn(Optional.of(invoice));
        assertThrows(BadRequestException.class, () -> invoiceService.updateInvoice(invoice.getId(), invoiceSaveDTO));
    }

    @Test
    void shouldReturnPDFDataWhenExportToPdf() throws IOException {
        when(invoiceRepository.findById(any(UUID.class))).thenReturn(Optional.of(invoice));
        when(pdfGenerator.generateInvoicePDF(any(Invoice.class))).thenReturn(new byte[0]);

        byte[] result = invoiceService.exportInvoiceToPDF(invoice.getId());

        assertNotNull(result);
        verify(pdfGenerator, times(1)).generateInvoicePDF(invoice);
    }

    @Test
    void shouldReturnPageOfInvoiceDTOsFindByCriteria() {

        Pageable pageable = PageRequest.of(0, 10);
        InvoiceSearchCriteriaDTO criteria = new InvoiceSearchCriteriaDTO();
        Page<Invoice> invoicesPage = new PageImpl<>(List.of(invoice));

        when(invoiceRepository.findByFilters(any(InvoiceSearchCriteriaDTO.class), any(Pageable.class))).thenReturn(invoicesPage);
        when(invoiceMapper.toInvoiceDTO(any(Invoice.class))).thenReturn(invoiceDTO);

        Page<InvoiceDTO> result = invoiceService.findByCriteria(criteria, pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(invoiceRepository, times(1)).findByFilters(any(InvoiceSearchCriteriaDTO.class), any(Pageable.class));
    }

    @Test
    void shouldExportInvoiceToExcelByAllFilter() {
        // Arrange
        InvoiceDetailsSearchCriteriaDTO criteria = new InvoiceDetailsSearchCriteriaDTO();
        criteria.setCustomerId(UUID.randomUUID());
        criteria.setMonth(8);
        criteria.setYear(2024);

        Invoice invoice1 = new Invoice();
        invoice1.setInvoiceDetails(new ArrayList<>()); // Initialize to avoid null pointer
        Invoice invoice2 = new Invoice();
        invoice2.setInvoiceDetails(new ArrayList<>()); // Initialize to avoid null pointer

        List<Invoice> mockInvoices = Arrays.asList(invoice1, invoice2);
        Workbook mockWorkbook = mock(Workbook.class);

        when(invoiceRepository.findByFiltersForExcel(any(UUID.class), anyInt(), anyInt())).thenReturn(mockInvoices);
        mockedExcelGenerator.when(() -> ExcelGenerator.generateInvoiceExcel(mockInvoices)).thenReturn(mockWorkbook);

        // Act
        Workbook result = invoiceService.exportInvoiceToExcelByFilter(criteria);

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(mockWorkbook, result, "The returned workbook should match the mocked workbook");
        verify(invoiceRepository, times(1)).findByFiltersForExcel(any(UUID.class), anyInt(), anyInt());
    }

    @Test
    void shouldExportInvoiceToExcelByFilterCustomerId() {
        // Arrange
        InvoiceDetailsSearchCriteriaDTO criteria = new InvoiceDetailsSearchCriteriaDTO();
        criteria.setCustomerId(UUID.randomUUID());

        Invoice invoice1 = new Invoice();
        invoice1.setInvoiceDetails(new ArrayList<>());
        Invoice invoice2 = new Invoice();
        invoice2.setInvoiceDetails(new ArrayList<>());

        List<Invoice> mockInvoices = Arrays.asList(invoice1, invoice2);
        Workbook mockWorkbook = mock(Workbook.class);

        when(invoiceRepository.findByFiltersForExcel(any(UUID.class), isNull(), isNull())).thenReturn(mockInvoices);
        mockedExcelGenerator.when(() -> ExcelGenerator.generateInvoiceExcel(mockInvoices)).thenReturn(mockWorkbook);

        // Act
        Workbook result = invoiceService.exportInvoiceToExcelByFilter(criteria);

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(mockWorkbook, result, "The returned workbook should match the mocked workbook");
        verify(invoiceRepository, times(1)).findByFiltersForExcel(any(UUID.class), isNull(), isNull());
    }

    @Test
    void shouldExportInvoiceToExcelByFilterMonthAndYear() {
        // Arrange
        InvoiceDetailsSearchCriteriaDTO criteria = new InvoiceDetailsSearchCriteriaDTO();
        criteria.setMonth(8);
        criteria.setYear(2024);

        Invoice invoice1 = new Invoice();
        invoice1.setInvoiceDetails(new ArrayList<>());
        Invoice invoice2 = new Invoice();
        invoice2.setInvoiceDetails(new ArrayList<>());

        List<Invoice> mockInvoices = Arrays.asList(invoice1, invoice2);
        Workbook mockWorkbook = mock(Workbook.class);

        when(invoiceRepository.findByFiltersForExcel(isNull(), anyInt(), anyInt())).thenReturn(mockInvoices);
        mockedExcelGenerator.when(() -> ExcelGenerator.generateInvoiceExcel(mockInvoices)).thenReturn(mockWorkbook);

        // Act
        Workbook result = invoiceService.exportInvoiceToExcelByFilter(criteria);

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(mockWorkbook, result, "The returned workbook should match the mocked workbook");
        verify(invoiceRepository, times(1)).findByFiltersForExcel(isNull(), anyInt(), anyInt());
    }

    @Test
    void shouldExportInvoiceToExcelByFilterMonthOnly() {
        // Arrange
        InvoiceDetailsSearchCriteriaDTO criteria = new InvoiceDetailsSearchCriteriaDTO();
        criteria.setMonth(8);

        Invoice invoice1 = new Invoice();
        invoice1.setInvoiceDetails(new ArrayList<>());
        Invoice invoice2 = new Invoice();
        invoice2.setInvoiceDetails(new ArrayList<>());

        List<Invoice> mockInvoices = Arrays.asList(invoice1, invoice2);
        Workbook mockWorkbook = mock(Workbook.class);

        when(invoiceRepository.findByFiltersForExcel(isNull(), anyInt(), isNull())).thenReturn(mockInvoices);
        mockedExcelGenerator.when(() -> ExcelGenerator.generateInvoiceExcel(mockInvoices)).thenReturn(mockWorkbook);

        // Act
        Workbook result = invoiceService.exportInvoiceToExcelByFilter(criteria);

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(mockWorkbook, result, "The returned workbook should match the mocked workbook");
        verify(invoiceRepository, times(1)).findByFiltersForExcel(isNull(), anyInt(), isNull());
    }


    @Test
    void shouldExportInvoiceToExcelByFilterYearOnly() {
        // Arrange
        InvoiceDetailsSearchCriteriaDTO criteria = new InvoiceDetailsSearchCriteriaDTO();
        criteria.setYear(2024);

        Invoice invoice1 = new Invoice();
        invoice1.setInvoiceDetails(new ArrayList<>());
        Invoice invoice2 = new Invoice();
        invoice2.setInvoiceDetails(new ArrayList<>());

        List<Invoice> mockInvoices = Arrays.asList(invoice1, invoice2);
        Workbook mockWorkbook = mock(Workbook.class);

        when(invoiceRepository.findByFiltersForExcel(isNull(), isNull(), anyInt())).thenReturn(mockInvoices);
        mockedExcelGenerator.when(() -> ExcelGenerator.generateInvoiceExcel(mockInvoices)).thenReturn(mockWorkbook);

        // Act
        Workbook result = invoiceService.exportInvoiceToExcelByFilter(criteria);

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(mockWorkbook, result, "The returned workbook should match the mocked workbook");
        verify(invoiceRepository, times(1)).findByFiltersForExcel(isNull(), isNull(), anyInt());
    }


    @Test
    void getInvoicesRevenue_ShouldReturnRevenueShowDTO() {
        Date date = new Date();
        String revenueBy = "day";
        double expectedRevenue = 500.0;

        when(invoiceRepository.findTotalRevenueByDay(any(Date.class))).thenReturn(expectedRevenue);

        RevenueShowDTO revenue = invoiceService.getInvoicesRevenue(date, revenueBy);

        assertNotNull(revenue);
        assertEquals(expectedRevenue, revenue.getAmount());
    }

    @Test
    void getInvoicesRevenue_ShouldReturnRevenueShowDTOMonth() {
        Date date = new Date();
        String revenueBy = "month";
        double expectedRevenue = 500.0;

        // Convert the date to month and year integers for the test
        int month = 8; // Example month
        int year = 2024; // Example year

        when(invoiceRepository.findTotalRevenueByMonth(year, month)).thenReturn(expectedRevenue);

        RevenueShowDTO revenue = invoiceService.getInvoicesRevenue(date, revenueBy);

        assertNotNull(revenue);
        assertEquals(expectedRevenue, revenue.getAmount());
    }

    @Test
    void getInvoicesRevenue_ShouldReturnRevenueShowDTOYear() {
        Date date = new Date();
        String revenueBy = "year";
        double expectedRevenue = 500.0;

        // Convert the date to year integer for the test
        int year = 2024; // Example year

        when(invoiceRepository.findTotalRevenueByYear(year)).thenReturn(expectedRevenue);

        RevenueShowDTO revenue = invoiceService.getInvoicesRevenue(date, revenueBy);

        assertNotNull(revenue);
        assertEquals(expectedRevenue, revenue.getAmount());
    }
}
