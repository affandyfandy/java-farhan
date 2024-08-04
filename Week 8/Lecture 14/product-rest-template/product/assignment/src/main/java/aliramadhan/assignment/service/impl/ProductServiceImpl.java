package aliramadhan.assignment.service.impl;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private RestTemplate restTemplate;

    private final String SUPPLIER_SERVICE_URL = "http://localhost:8085/api/v1/suppliers/";

    @Override
    public ProductDTO saveProduct(ProductSaveDTO productSaveDTO) {
        // Validate supplier ID
        if (productSaveDTO.getSupplier_id() != null) {
            String url = SUPPLIER_SERVICE_URL + productSaveDTO.getSupplier_id();
            SupplierDTO supplierDTO = restTemplate.getForObject(url, SupplierDTO.class);
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

        // Fetch supplier information using RestTemplate
        if (product.getSupplierId() != null) {
            String url = SUPPLIER_SERVICE_URL + product.getSupplierId();
            SupplierDTO supplierDTO = restTemplate.getForObject(url, SupplierDTO.class);
            productShowDTO.setSupplier(supplierDTO);
        }

        return productShowDTO;
    }

    @Override
    public Page<ProductShowDTO> getAllProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);

        Page<ProductShowDTO> productShowDTOs = products.map(product -> {
            ProductShowDTO productShowDTO = productMapper.toShowDTO(product);

            // Fetch supplier information using RestTemplate if supplierId is present
            if (product.getSupplierId() != null) {
                String url = SUPPLIER_SERVICE_URL + product.getSupplierId();
                SupplierDTO supplierDTO = restTemplate.getForObject(url, SupplierDTO.class);
                productShowDTO.setSupplier(supplierDTO);
            }

            return productShowDTO;
        });

        return productShowDTOs;
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductSaveDTO productSaveDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productSaveDTO.getName());
        product.setPrice(productSaveDTO.getPrice());

        product = productRepository.save(product);

        return productMapper.toProductDTO(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }
}
