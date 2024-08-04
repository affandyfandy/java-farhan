package aliramadhan.assignment.service;

import aliramadhan.assignment.dto.ProductDTO;
import aliramadhan.assignment.dto.ProductSaveDTO;
import aliramadhan.assignment.dto.ProductShowDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductDTO saveProduct(ProductSaveDTO productSaveDTO);
    ProductShowDTO getProductById(String id);
    Page<ProductShowDTO> getAllProducts(Pageable pageable);
    ProductDTO updateProduct(String id, ProductSaveDTO productSaveDTO);
    void deleteProduct(String id);
}
