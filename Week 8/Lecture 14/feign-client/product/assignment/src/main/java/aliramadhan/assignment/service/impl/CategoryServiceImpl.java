package aliramadhan.assignment.service.impl;

import aliramadhan.assignment.data.model.Category;
import aliramadhan.assignment.data.repository.CategoryRepository;
import aliramadhan.assignment.dto.*;
import aliramadhan.assignment.mapper.CategoryMapper;
import aliramadhan.assignment.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public CategoryDTO saveCategory(CategorySaveDTO categorySaveDTO) {
        Category category = categoryMapper.toCategory(categorySaveDTO);
        category = categoryRepository.save(category);
        return categoryMapper.toCategoryDTO(category);
    }

    @Override
    public CategoryShowDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return categoryMapper.toShowDTO(category);
    }

    @Override
    public Page<CategoryShowDTO> getAllCategories(Pageable pageable) {
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        Page<Category> categories = categoryRepository.findAll(sortedPageable);
        return categories.map(categoryMapper::toShowDTO);
    }


    @Override
    public CategoryDTO updateCategory(Long id, CategorySaveDTO categorySaveDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        category.setName(categorySaveDTO.getName());
        category = categoryRepository.save(category);
        return categoryMapper.toCategoryDTO(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}