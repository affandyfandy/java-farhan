package com.example.fpt_midterm_pos.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.fpt_midterm_pos.data.model.Customer;
import com.example.fpt_midterm_pos.dto.CustomerInvoiceDTO;
import com.example.fpt_midterm_pos.dto.InvoiceDTO;
import com.example.fpt_midterm_pos.dto.InvoiceDetailDTO;
import com.example.fpt_midterm_pos.dto.InvoiceDetailsSearchCriteriaDTO;
import com.example.fpt_midterm_pos.dto.InvoiceSaveDTO;
import com.example.fpt_midterm_pos.dto.InvoiceSearchCriteriaDTO;
import com.example.fpt_midterm_pos.dto.RevenueShowDTO;
import com.example.fpt_midterm_pos.exception.GlobalExceptionHandler;
import com.example.fpt_midterm_pos.service.CustomerService;
import com.example.fpt_midterm_pos.service.InvoiceService;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
class InvoiceControllerTest {

        @InjectMocks
        private InvoiceController invoiceController;

        @Mock
        private InvoiceService invoiceService;

        @Mock
        private CustomerService customerService;

        private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        private MockMvc mockMvc;

        @BeforeEach
        public void setUp() {
                MockitoAnnotations.openMocks(this);
                mockMvc = MockMvcBuilders.standaloneSetup(invoiceController)
                                .setControllerAdvice(new GlobalExceptionHandler())
                                .build();
        }

        @Test
        void shouldGetInvoices_NoContent() throws Exception {

                Pageable pageable = PageRequest.of(0, 20);
                List<InvoiceDTO> invoiceDTOList = new ArrayList<>();
                Page<InvoiceDTO> invoices = new PageImpl<>(invoiceDTOList, pageable, 1);

                when(invoiceService.findByCriteria(any(InvoiceSearchCriteriaDTO.class), any(Pageable.class)))
                                .thenReturn(invoices);

                mockMvc.perform(get("/api/v1/invoices")
                                .param("page", "0")
                                .param("size", "20"))
                                .andExpect(status().isNoContent());

                verify(invoiceService, times(1)).findByCriteria(any(InvoiceSearchCriteriaDTO.class),
                                any(Pageable.class));
        }

        @Test
        void shouldGetInvoices_OK() throws Exception {

                Pageable pageable = PageRequest.of(0, 20);
                CustomerInvoiceDTO customerInvoiceDTO = new CustomerInvoiceDTO();
                List<InvoiceDetailDTO> invoiceDetails = new ArrayList<>();
                InvoiceDTO invoiceDTO = new InvoiceDTO(UUID.randomUUID(), 200.0, new Date(), customerInvoiceDTO,
                                invoiceDetails);
                Page<InvoiceDTO> invoicePage = new PageImpl<>(List.of(invoiceDTO), pageable, 1);

                when(invoiceService.findByCriteria(any(InvoiceSearchCriteriaDTO.class), any(Pageable.class)))
                                .thenReturn(invoicePage);

                ObjectMapper objectMapper = new ObjectMapper();
                String expectedJson = objectMapper.writeValueAsString(invoicePage);

                mockMvc.perform(get("/api/v1/invoices")
                                .param("page", "0")
                                .param("size", "20")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andExpect(content().json(expectedJson));
        }

        @Test
        void shouldGetRevenue() throws Exception {
                RevenueShowDTO revenueShowDTO = new RevenueShowDTO();
                LocalDate today = LocalDate.now();
                String datePart = today.format(dateFormatter);
                String date = String.format("%s", datePart);

                when(invoiceService.getInvoicesRevenue(any(Date.class), anyString()))
                                .thenReturn(revenueShowDTO);

                mockMvc.perform(get("/api/v1/invoices/revenue")
                                .param("date", date)
                                .param("revenueBy", "month"))
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

                verify(invoiceService, times(1)).getInvoicesRevenue(any(Date.class), anyString());
        }

        @Test
        void shouldCreateInvoice() throws Exception {
                InvoiceDTO invoiceDTO = new InvoiceDTO();

                when(invoiceService.createInvoice(any(InvoiceSaveDTO.class)))
                                .thenReturn(invoiceDTO);

                mockMvc.perform(post("/api/v1/invoices")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{ \"customerId\": \"123e4567-e89b-12d3-a456-426614174000\" }"))
                                .andExpect(status().isCreated());

                verify(invoiceService, times(1)).createInvoice(any(InvoiceSaveDTO.class));
        }

        @Test
        void shouldUpdateInvoice() throws Exception {
                UUID id = UUID.randomUUID();
                InvoiceDTO invoiceDTO = new InvoiceDTO();

                when(invoiceService.updateInvoice(any(UUID.class), any(InvoiceSaveDTO.class)))
                                .thenReturn(invoiceDTO);

                mockMvc.perform(put("/api/v1/invoices/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{ \"customerId\": \"123e4567-e89b-12d3-a456-426614174000\" }"))
                                .andExpect(status().isOk());

                verify(invoiceService, times(1)).updateInvoice(any(UUID.class), any(InvoiceSaveDTO.class));
        }

        @Test
        void shouldExportInvoiceToPDF() throws Exception {
                UUID id = UUID.randomUUID();
                byte[] pdfBytes = new byte[10];

                when(invoiceService.exportInvoiceToPDF(any(UUID.class)))
                                .thenReturn(pdfBytes);

                mockMvc.perform(get("/api/v1/invoices/{id}/export", id))
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_PDF));

                verify(invoiceService, times(1)).exportInvoiceToPDF(any(UUID.class));
        }

        @Test
        void shouldExportInvoiceToExcel_NoCriteria() throws Exception {
                mockMvc.perform(get("/api/v1/invoices/excel"))
                                .andExpect(status().isBadRequest())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(content().json(
                                                "{\"message\": \"Please select at least one criterion: Customer ID, Month, or Year.\"}"));
        }

        @Test
        void shouldExportInvoiceToExcel_WithCriteria() throws Exception {
                InvoiceDetailsSearchCriteriaDTO criteria = new InvoiceDetailsSearchCriteriaDTO();
                Workbook workbook = mock(Workbook.class);
                UUID customerId = UUID.randomUUID();
                Customer customer = new Customer();
                customer.setName("John Doe");

                criteria.setCustomerId(customerId);
                criteria.setMonth(8);
                criteria.setYear(2024);

                when(customerService.findById(any(UUID.class))).thenReturn(customer);
                when(invoiceService.exportInvoiceToExcelByFilter(any(InvoiceDetailsSearchCriteriaDTO.class)))
                                .thenReturn(workbook);

                mockMvc.perform(get("/api/v1/invoices/excel")
                                .param("customerId", customerId.toString())
                                .param("month", "8")
                                .param("year", "2024"))
                                .andExpect(status().isOk())
                                .andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION,
                                                "attachment; filename=invoice_report_John_Doe_8_2024.xlsx"));

                verify(invoiceService, times(1))
                                .exportInvoiceToExcelByFilter(any(InvoiceDetailsSearchCriteriaDTO.class));
                verify(customerService, times(1)).findById(any(UUID.class));
        }

        @Test
        void shouldExportInvoiceToExcel_IOError() throws Exception {
                InvoiceDetailsSearchCriteriaDTO criteria = new InvoiceDetailsSearchCriteriaDTO();
                criteria.setCustomerId(UUID.randomUUID());
                criteria.setMonth(8);
                criteria.setYear(2024);

                when(invoiceService.exportInvoiceToExcelByFilter(any(InvoiceDetailsSearchCriteriaDTO.class)))
                                .thenThrow(new RuntimeException("Error"));

                mockMvc.perform(get("/api/v1/invoices/excel")
                                .param("customerId", criteria.getCustomerId().toString())
                                .param("month", "8")
                                .param("year", "2024"))
                                .andExpect(status().isInternalServerError())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

                verify(invoiceService, times(0))
                                .exportInvoiceToExcelByFilter(any(InvoiceDetailsSearchCriteriaDTO.class));
        }
}
