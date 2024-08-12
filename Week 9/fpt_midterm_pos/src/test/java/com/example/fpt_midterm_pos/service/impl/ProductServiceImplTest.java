package com.example.fpt_midterm_pos.service.impl;

import java.io.IOException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;

import com.example.fpt_midterm_pos.data.model.Product;
import com.example.fpt_midterm_pos.data.model.Status;
import com.example.fpt_midterm_pos.data.repository.ProductRepository;
import com.example.fpt_midterm_pos.dto.ProductDTO;
import com.example.fpt_midterm_pos.dto.ProductSaveDTO;
import com.example.fpt_midterm_pos.dto.ProductSearchCriteriaDTO;
import com.example.fpt_midterm_pos.dto.ProductShowDTO;
import com.example.fpt_midterm_pos.exception.BadRequestException;
import com.example.fpt_midterm_pos.exception.DuplicateStatusException;
import com.example.fpt_midterm_pos.exception.ResourceNotFoundException;
import com.example.fpt_midterm_pos.mapper.ProductMapper;
import com.example.fpt_midterm_pos.utils.FileUtils;

class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    private ProductSearchCriteriaDTO searchCriteria;
    private Pageable pageable;
    private Page<Product> productPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindProductsByCriteria_withSortOptions() {
        UUID productId = UUID.randomUUID();
        Product product = createProduct(productId, "Test Product", 100.0, 10, Status.ACTIVE);

        searchCriteria = new ProductSearchCriteriaDTO();
        searchCriteria.setName("Test");
        searchCriteria.setMinPrice(50.0);
        searchCriteria.setMaxPrice(150.0);
        searchCriteria.setSortByName("asc");
        searchCriteria.setSortByPrice("desc");

        pageable = PageRequest.of(0, 10);
        productPage = new PageImpl<>(Collections.singletonList(product));

        when(productRepository.findByFilters(any(), any(), any(), any(), any())).thenReturn(productPage);
        when(productMapper.toShowDTO(any())).thenReturn(new ProductShowDTO());

        Page<ProductShowDTO> result = productService.findByCriteria(searchCriteria, pageable);

        assertNotNull(result);
        verify(productRepository).findByFilters(any(), eq("Test"), eq(50.0), eq(150.0), any(Pageable.class));
    }

    @Test
    void shouldCreateProduct() {
        ProductSaveDTO saveDTO = new ProductSaveDTO("Product", 100.0, 10);
        Product product = new Product();
        UUID productId = UUID.randomUUID();
        product.setId(productId);
        ProductDTO expectedProductDTO = new ProductDTO(productId, "Product", 100.0, Status.ACTIVE, 10);

        when(productMapper.toProduct(saveDTO)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toProductDTO(product)).thenReturn(expectedProductDTO);

        ProductDTO result = productService.createProduct(saveDTO);

        verify(productRepository, times(1)).save(product);
        assertThat(result).isEqualTo(expectedProductDTO);
    }

    @Test
    void shouldUpdateProduct() {
        UUID productId = UUID.randomUUID();
        ProductSaveDTO saveDTO = new ProductSaveDTO("Updated Product", 150.0, 20);
        Product product = createProduct(productId, "Old Product", 100.0, 10, Status.ACTIVE);
        ProductDTO updatedProductDTO = new ProductDTO(productId, "Updated Product", 150.0, Status.ACTIVE, 20);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productMapper.toProduct(saveDTO)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toProductDTO(product)).thenReturn(updatedProductDTO);

        ProductDTO result = productService.updateProduct(productId, saveDTO);

        verify(productRepository, times(1)).save(product);
        assertThat(result).isEqualTo(updatedProductDTO);
    }

    @Test
    void shouldThrowException_whenUpdatingNonExistingProduct() {
        UUID productId = UUID.randomUUID();
        ProductSaveDTO saveDTO = new ProductSaveDTO("Updated Product", 150.0, 20);

        when(productRepository.findById(productId)).thenReturn(Optional.empty());


        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> productService.updateProduct(productId, saveDTO)
        );

        assertThat(exception.getMessage()).contains("Product not found");
    }

    @Test
    void shouldUpdateProductStatus_toDeactive() {
        UUID productId = UUID.randomUUID();
        Product product = createProduct(productId, "Product", 100.0, 10, Status.ACTIVE);
        ProductDTO updatedProductDTO = new ProductDTO(productId, "Product", 100.0, Status.DEACTIVATED, 10);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toProductDTO(product)).thenReturn(updatedProductDTO);

        ProductDTO result = productService.updateProductStatus(productId, Status.DEACTIVATED);

        verify(productRepository, times(1)).save(product);
        assertThat(result).isEqualTo(updatedProductDTO);
    }

    @Test
    void shouldUpdateProductStatusFromDeactivatedToActive() {
        UUID productId = UUID.randomUUID();
        Product product = createProduct(productId, "Product", 100.0, 10, Status.DEACTIVATED);
        ProductDTO updatedProductDTO = new ProductDTO(productId, "Product", 100.0, Status.ACTIVE, 10);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toProductDTO(product)).thenReturn(updatedProductDTO);

        ProductDTO result = productService.updateProductStatus(productId, Status.ACTIVE);

        verify(productRepository, times(1)).save(product);
        assertThat(result).isEqualTo(updatedProductDTO);
    }



    @Test
    void shouldThrowException_whenUpdatingProductStatus_toActive_ifProductNotFound() {
        UUID productId = UUID.randomUUID();

        when(productRepository.findById(productId)).thenReturn(Optional.empty());


        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () ->  productService.updateProductStatus(productId, Status.ACTIVE)
        );

        assertThat(exception.getMessage()).contains("Product not found");
    }

    @Test
    void shouldThrowException_whenUpdatingProductStatus_withDuplicateActiveStatus() {
        UUID productId = UUID.randomUUID();
        Product product = createProduct(productId, "Product", 100.0, 10, Status.ACTIVE);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        DuplicateStatusException exception = assertThrows(
                DuplicateStatusException.class,
                () ->  productService.updateProductStatus(productId, Status.ACTIVE)
        );

        assertThat(exception.getMessage()).contains("Product status is already ACTIVE");
    }

    @Test
    void shouldThrowException_whenUpdatingProductStatus_withDuplicateDEACTIVEDStatus() {
        UUID productId = UUID.randomUUID();
        Product product = createProduct(productId, "Product", 100.0, 10, Status.DEACTIVATED);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        DuplicateStatusException exception = assertThrows(
                DuplicateStatusException.class,
                () ->  productService.updateProductStatus(productId, Status.DEACTIVATED)
        );

        assertThat(exception.getMessage()).contains("Product status is already DEACTIVATED");
    }

    @Test
    void shouldSaveProductsFromExcel() {
        List<ProductSaveDTO> saveDTOs = List.of(new ProductSaveDTO("Product", 100.0, 10));
        List<Product> products = List.of(mock(Product.class));
        List<ProductDTO> productDTOs = List.of(new ProductDTO());

        MockMultipartFile fileExcel = new MockMultipartFile(
                "file", "products.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                "name,price,quantity\nProduct,100.0,10".getBytes()
        );

        try (MockedStatic<FileUtils> mockedStatic = mockStatic(FileUtils.class)) {
            mockedStatic.when(() -> FileUtils.readProductsFromExcel(fileExcel)).thenReturn(saveDTOs);

            when(productMapper.toProductList(saveDTOs)).thenReturn(products);
            when(productRepository.saveAll(products)).thenReturn(products);
            when(productMapper.toProductDTOList(products)).thenReturn(productDTOs);

            List<ProductDTO> result = productService.saveProductsFromExcel(fileExcel);

            verify(productRepository, times(1)).saveAll(products);
            assertThat(result).isEqualTo(productDTOs);
        }
    }


    @Test
    void shouldHandleZeroQuantity_whenSavingProductsFromExcel() {
        List<ProductSaveDTO> saveDTOs = List.of(new ProductSaveDTO("Product", 100.0, 0));
        List<Product> products = List.of(mock(Product.class));
        List<ProductDTO> productDTOs = List.of(new ProductDTO());

        MockMultipartFile fileExcel = new MockMultipartFile(
                "file", "products.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                "name,price,quantity\nProduct,100.0,0".getBytes()
        );

        try (MockedStatic<FileUtils> mockedStatic = mockStatic(FileUtils.class)) {
            mockedStatic.when(() -> FileUtils.readProductsFromExcel(fileExcel)).thenReturn(saveDTOs);

            when(productMapper.toProductList(saveDTOs)).thenReturn(products);
            when(productRepository.saveAll(products)).thenReturn(products);
            when(productMapper.toProductDTOList(products)).thenReturn(productDTOs);

            List<ProductDTO> result = productService.saveProductsFromExcel(fileExcel);

            verify(productRepository, times(1)).saveAll(products);
            assertThat(result).isEqualTo(productDTOs);
        }
    }

    @Test
    void shouldThrowBadRequestException_whenExcelFileIsInvalid(){
        MockMultipartFile fileExcel = new MockMultipartFile(
                "file", "invalid_products.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                "invalid content".getBytes()
        );

        try (MockedStatic<FileUtils> mockedStatic = mockStatic(FileUtils.class)) {
            mockedStatic.when(() -> FileUtils.readProductsFromExcel(fileExcel))
                    .thenThrow(new BadRequestException("Invalid Excel file"));

            BadRequestException exception = assertThrows(
                    BadRequestException.class,
                    () ->   productService.saveProductsFromExcel(fileExcel)
            );

            assertThat(exception.getMessage()).contains("Invalid Excel file");
        }
    }

    @Test
    void shouldThrowBadRequestException_whenIOExceptionIsThrown() {
        MockMultipartFile fileExcel = new MockMultipartFile(
                "file", "products.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                "name,price,quantity\nProduct,100.0,10".getBytes()
        );

        try (MockedStatic<FileUtils> mockedStatic = mockStatic(FileUtils.class)) {
            mockedStatic.when(() -> FileUtils.readProductsFromExcel(fileExcel))
                    .thenThrow(new IOException("I/O error"));

            BadRequestException exception = assertThrows(
                    BadRequestException.class,
                    () -> productService.saveProductsFromExcel(fileExcel)
            );

            assertThat(exception.getMessage()).contains("Error reading Excel file: I/O error");
        }
    }

    @Test
    void shouldThrowBadRequestException_whenIllegalArgumentExceptionIsThrown() {
        MockMultipartFile fileExcel = new MockMultipartFile(
                "file", "invalid_products.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                "invalid content".getBytes()
        );

        try (MockedStatic<FileUtils> mockedStatic = mockStatic(FileUtils.class)) {
            mockedStatic.when(() -> FileUtils.readProductsFromExcel(fileExcel))
                    .thenThrow(new IllegalArgumentException("Invalid file format"));

            BadRequestException exception = assertThrows(
                    BadRequestException.class,
                    () -> productService.saveProductsFromExcel(fileExcel)
            );

            assertThat(exception.getMessage()).contains("Invalid file format. Only Excel files are accepted.");
        }
    }


    private Product createProduct(UUID id, String name, double price, int quantity, Status status) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setStatus(status);
        return product;
    }
}
