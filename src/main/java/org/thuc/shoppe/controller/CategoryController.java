package org.thuc.shoppe.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thuc.shoppe.model.dto.CategoryDto;
import org.thuc.shoppe.model.response.ResponseSuccessDto;
import org.thuc.shoppe.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @GetMapping
    public ResponseEntity<ResponseSuccessDto<List<CategoryDto>>> getAll() {
        log.debug("Request to get all categories");
        List<CategoryDto> list = categoryService.getAllCategories();
        return ResponseEntity.ok(ResponseSuccessDto.success(list));
    }
    @GetMapping("{categoryId}")
    public ResponseEntity<ResponseSuccessDto<CategoryDto>> getCategoryById(@PathVariable Long categoryId) {
        log.debug("Request to get category by id: {}", categoryId);
        CategoryDto category = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(ResponseSuccessDto.success(category));
    }
}
