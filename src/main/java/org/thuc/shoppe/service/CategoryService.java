package org.thuc.shoppe.service;

import org.thuc.shoppe.model.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(Long categoryId);
}
