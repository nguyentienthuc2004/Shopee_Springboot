package org.thuc.shoppe.mapper;

import lombok.Builder;
import org.mapstruct.Mapper;
import org.thuc.shoppe.entity.Category;
import org.thuc.shoppe.model.dto.CategoryDto;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    public CategoryDto toCategoryDto(Category category);
}
