package aliramadhan.assignment.controller;

import aliramadhan.assignment.dto.*;
import aliramadhan.assignment.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> saveProduct(@RequestBody CategorySaveDTO categorySaveDTO) {
        CategoryDTO category = categoryService.saveCategory(categorySaveDTO);
        return ResponseEntity.ok().body(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryShowDTO> getProductById(@PathVariable Long id) {
        CategoryShowDTO categoryShowDTO = categoryService.getCategoryById(id);
        return ResponseEntity.ok().body(categoryShowDTO);
    }

    @GetMapping
    public ResponseEntity<Page<CategoryShowDTO>> getAllCategories(Pageable pageable) {
        Page<CategoryShowDTO> categories = categoryService.getAllCategories(pageable);
        return ResponseEntity.ok().body(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateProduct(@PathVariable Long id, @RequestBody CategorySaveDTO categorySaveDTO) {
        CategoryDTO productDTO = categoryService.updateCategory(id, categorySaveDTO);
        return ResponseEntity.ok().body(productDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}