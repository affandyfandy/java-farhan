package aliramadhan.assignment.controller;

import aliramadhan.assignment.dto.ProductDTO;
import aliramadhan.assignment.dto.ProductSaveDTO;
import aliramadhan.assignment.dto.ProductShowDTO;
import aliramadhan.assignment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductSaveDTO productSaveDTO) {
        ProductDTO productDTO = productService.saveProduct(productSaveDTO);
        return ResponseEntity.ok().body(productDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductShowDTO> getProductById(@PathVariable Long id) {
        ProductShowDTO productShowDTO = productService.getProductById(id);
        return ResponseEntity.ok().body(productShowDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ProductShowDTO>> getAllProducts(Pageable pageable) {
        Page<ProductShowDTO> products = productService.getAllProducts(pageable);
        return ResponseEntity.ok().body(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductSaveDTO productSaveDTO) {
        ProductDTO productDTO = productService.updateProduct(id, productSaveDTO);
        return ResponseEntity.ok().body(productDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}