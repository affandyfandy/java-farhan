package aliramadhan.assignment.service;

import aliramadhan.assignment.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryDTO saveCategory(CategorySaveDTO categorySaveDTO);
    CategoryShowDTO getCategoryById(Long id);
    Page<CategoryShowDTO> getAllCategories(Pageable pageable);
    CategoryDTO updateCategory(Long id, CategorySaveDTO categorySaveDTO);
    void deleteCategory(Long id);
}
