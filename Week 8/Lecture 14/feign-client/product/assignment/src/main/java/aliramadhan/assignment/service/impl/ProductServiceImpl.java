package aliramadhan.assignment.service.impl;

import aliramadhan.assignment.client.SupplierClient;
import aliramadhan.assignment.data.model.Product;
import aliramadhan.assignment.data.repository.ProductRepository;
import aliramadhan.assignment.dto.ProductDTO;
import aliramadhan.assignment.dto.ProductSaveDTO;
import aliramadhan.assignment.dto.ProductShowDTO;
import aliramadhan.assignment.dto.SupplierDTO;
import aliramadhan.assignment.mapper.ProductMapper;
import aliramadhan.assignment.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;


    @Autowired
    private SupplierClient supplierClient;

    //    @Override
//    public ProductDTO saveProduct(ProductSaveDTO productSaveDTO) {
//        Product product = productMapper.toProduct(productSaveDTO);
//        product = productRepository.save(product);
//        return productMapper.toProductDTO(product);
//    }
//
//    @Override
//    public ProductShowDTO getProductById(Long id) {
//        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
//        return productMapper.toShowDTO(product);
//    }
    @Override
    public ProductDTO saveProduct(ProductSaveDTO productSaveDTO) {
        // Validate supplier ID
        if (productSaveDTO.getSupplier_id() != null) {
            SupplierDTO supplierDTO = supplierClient.getSupplierById(productSaveDTO.getSupplier_id());
            if (supplierDTO == null) {
                throw new RuntimeException("Supplier not found");
            }
        }

        Product product = productMapper.toProduct(productSaveDTO);
        product = productRepository.save(product);
        return productMapper.toProductDTO(product);
    }

    @Override
    public ProductShowDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        ProductShowDTO productShowDTO = productMapper.toShowDTO(product);

        // Fetch supplier information using Feign client
        if (product.getSupplierId() != null) {
            SupplierDTO supplierDTO = supplierClient.getSupplierById(product.getSupplierId());
            productShowDTO.setSupplier(supplierDTO);
        }

        return productShowDTO;
    }

//    @Override
//    public Page<ProductShowDTO> getAllProducts(Pageable pageable) {
//        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
//        Page<Product> products = productRepository.findAll(sortedPageable);
//        return products.map(productMapper::toShowDTO);
//    }

    @Override
    public Page<ProductShowDTO> getAllProducts(Pageable pageable) {
        // Use the provided Pageable without additional sorting logic
        Page<Product> products = productRepository.findAll(pageable);

        Page<ProductShowDTO> productShowDTOs = products.map(product -> {
            // Convert Product to ProductShowDTO using MapStruct or any other mapper
            ProductShowDTO productShowDTO = productMapper.toShowDTO(product);

            // Fetch supplier information using Feign client if supplierId is present
            if (product.getSupplierId() != null) {
                SupplierDTO supplierDTO = supplierClient.getSupplierById(product.getSupplierId());
                productShowDTO.setSupplier(supplierDTO);
            }

            return productShowDTO;
        });

        return productShowDTOs;
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductSaveDTO productSaveDTO) {
        // Fetch the existing product or throw an exception if not found
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Update product fields with the new values from the DTO
        product.setName(productSaveDTO.getName());
        product.setPrice(productSaveDTO.getPrice());
        // Add any other fields from ProductSaveDTO that need to be updated

        // Save the updated product
        product = productRepository.save(product);

        // Return the updated product as a ProductDTO
        return productMapper.toProductDTO(product);
    }

    @Override
    public void deleteProduct(Long id) {
        // Check if the product exists before attempting to delete
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        // Perform the delete operation
        productRepository.deleteById(id);
    }

}