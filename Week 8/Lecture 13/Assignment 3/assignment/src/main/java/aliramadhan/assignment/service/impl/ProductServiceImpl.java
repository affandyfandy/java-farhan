package aliramadhan.assignment.service.impl;

import aliramadhan.assignment.data.model.Product;
import aliramadhan.assignment.data.repository.ProductRepository;
import aliramadhan.assignment.dto.ProductDTO;
import aliramadhan.assignment.dto.ProductSaveDTO;
import aliramadhan.assignment.dto.ProductShowDTO;
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

    @Override
    public ProductDTO saveProduct(ProductSaveDTO productSaveDTO) {
        Product product = productMapper.toProduct(productSaveDTO);
        product.setId(UUID.randomUUID().toString());
        product = productRepository.save(product);
        return ProductMapper.INSTANCE.toProductDTO(product);
    }

    @Override
    public ProductShowDTO getProductById(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return productMapper.toProductShowDTO(product);
    }
    @Override
    public  Page<ProductShowDTO> getAllProducts(Pageable pageable){
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        Page<Product> products = productRepository.findAll(sortedPageable);
        return products.map(productMapper::toProductShowDTO);
    }

    @Override
    public ProductDTO updateProduct(String id, ProductSaveDTO productSaveDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productSaveDTO.getName());
        product.setPrice(productSaveDTO.getPrice());
        product = productRepository.save(product);
        return ProductMapper.INSTANCE.toProductDTO(product);
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }



}
