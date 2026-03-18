package org.thuc.shoppe.mapper;

import lombok.Builder;
import org.thuc.shoppe.entity.Category;
import org.thuc.shoppe.model.dto.CategoryDto;

@Builder
public class CategoryMapper {
    public static CategoryDto toCategoryDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
