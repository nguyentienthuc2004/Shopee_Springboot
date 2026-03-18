package org.thuc.shoppe.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thuc.shoppe.constant.ApiMessages;
import org.thuc.shoppe.entity.Category;
import org.thuc.shoppe.exception.NotFoundException;
import org.thuc.shoppe.mapper.CategoryMapper;
import org.thuc.shoppe.model.dto.CategoryDto;
import org.thuc.shoppe.repo.CategoryRepository;
import org.thuc.shoppe.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(c -> CategoryDto.builder().id(c.getId()).name(c.getName()).build())
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException(ApiMessages.CATEGORY_NOT_FOUND + categoryId));
        return categoryMapper.toCategoryDto(category);
    }
}
