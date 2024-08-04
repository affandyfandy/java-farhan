package aliramadhan.assignment.service;

import aliramadhan.assignment.dto.ProductDTO;
import aliramadhan.assignment.dto.ProductSaveDTO;
import aliramadhan.assignment.dto.ProductShowDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductDTO saveProduct(ProductSaveDTO productSaveDTO);
    ProductShowDTO getProductById(Long id);
    Page<ProductShowDTO> getAllProducts(Pageable pageable);
    ProductDTO updateProduct(Long id, ProductSaveDTO productSaveDTO);
    void deleteProduct(Long id);
}
