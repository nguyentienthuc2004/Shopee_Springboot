package org.thuc.shoppe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public ResponseEntity<ResponseSuccessDto<List<CategoryDto>>> getAll() {
        List<CategoryDto> list = categoryService.getAllCategories();
        return ResponseEntity.ok(ResponseSuccessDto.success(list));
    }
}
