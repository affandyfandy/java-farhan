package com.example.fpt_midterm_pos.mapper;

import com.example.fpt_midterm_pos.data.model.Product;
import com.example.fpt_midterm_pos.data.model.Status;
import com.example.fpt_midterm_pos.dto.ProductDTO;
import com.example.fpt_midterm_pos.dto.ProductSaveDTO;
import com.example.fpt_midterm_pos.dto.ProductShowDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    private ProductMapper productMapper;

    @BeforeEach
    void initializeMapper() {
        productMapper = Mappers.getMapper(ProductMapper.class);
    }

    private Product createSampleProduct() {
        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setName("Sample Product");
        product.setPrice(100.0);
        product.setStatus(Status.ACTIVE);
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());
        return product;
    }

    private ProductDTO createSampleProductDTO() {
        return new ProductDTO(
                UUID.randomUUID(),
                "Sample Product",
                100.0,
                Status.ACTIVE,
                50
        );
    }

    private ProductSaveDTO createSampleProductSaveDTO() {
        return new ProductSaveDTO(
                "Sample Product",
                100.0,
                50
        );
    }

    private void assertProductAndDTOEquality(Product product, ProductDTO productDTO) {
        assertEquals(product.getId(), productDTO.getId());
        assertEquals(product.getName(), productDTO.getName());
        assertEquals(product.getPrice(), productDTO.getPrice());
        assertEquals(product.getStatus(), productDTO.getStatus());
    }

    @Test
    void shouldMapProductToProductDTO() {
        Product product = createSampleProduct();
        ProductDTO productDTO = productMapper.toProductDTO(product);

        assertProductAndDTOEquality(product, productDTO);
    }

    @Test
    void shouldMapProductDTOToProduct() {
        ProductDTO productDTO = createSampleProductDTO();
        Product product = productMapper.toProduct(productDTO);

        assertNull(product.getCreatedAt());
        assertNull(product.getUpdatedAt());
        assertEquals(productDTO.getName(), product.getName());
        assertEquals(productDTO.getPrice(), product.getPrice());
    }

    @Test
    void shouldMapProductToProductShowDTO() {
        Product product = createSampleProduct();
        ProductShowDTO productShowDTO = productMapper.toShowDTO(product);

        assertEquals(product.getId(), productShowDTO.getId());
        assertEquals(product.getName(), productShowDTO.getName());
        assertEquals(product.getPrice(), productShowDTO.getPrice());
    }

    @Test
    void shouldMapProductToProductSaveDTO() {
        Product product = createSampleProduct();
        ProductSaveDTO productSaveDTO = productMapper.toProductSaveDTO(product);

        assertEquals(product.getName(), productSaveDTO.getName());
        assertEquals(product.getPrice(), productSaveDTO.getPrice());
        assertEquals(product.getQuantity(), productSaveDTO.getQuantity());
    }

    @Test
    void shouldReturnNullWhenMappingNullProductToProductSaveDTO() {
        ProductSaveDTO productSaveDTO = productMapper.toProductSaveDTO(null);

        assertNull(productSaveDTO);
    }

    @Test
    void shouldMapProductSaveDTOToProduct() {
        ProductSaveDTO productSaveDTO = createSampleProductSaveDTO();
        Product product = productMapper.toProduct(productSaveDTO);

        assertEquals(productSaveDTO.getName(), product.getName());
        assertEquals(productSaveDTO.getPrice(), product.getPrice());
        assertEquals(productSaveDTO.getQuantity(), product.getQuantity());
    }

    @Test
    void shouldReturnNullWhenMappingNullProductSaveDTOToProduct() {
        Product product = productMapper.toProduct((ProductSaveDTO) null);

        assertNull(product);
    }

    @Test
    void shouldMapProductListToProductDTOList() {
        Product product1 = createSampleProduct();
        Product product2 = createSampleProduct();

        List<ProductDTO> productDTOList = productMapper.toProductDTOList(List.of(product1, product2));

        assertEquals(2, productDTOList.size());
        assertProductAndDTOEquality(product1, productDTOList.get(0));
        assertProductAndDTOEquality(product2, productDTOList.get(1));
    }

    @Test
    void shouldMapProductSaveDTOListToProductList() {
        ProductSaveDTO productSaveDTO1 = createSampleProductSaveDTO();
        ProductSaveDTO productSaveDTO2 = createSampleProductSaveDTO();

        List<Product> productList = productMapper.toProductList(List.of(productSaveDTO1, productSaveDTO2));

        assertEquals(2, productList.size());
        assertEquals(productSaveDTO1.getName(), productList.get(0).getName());
        assertEquals(productSaveDTO2.getName(), productList.get(1).getName());
    }
}
