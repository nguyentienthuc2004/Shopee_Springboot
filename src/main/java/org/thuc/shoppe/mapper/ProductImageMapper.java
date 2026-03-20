package org.thuc.shoppe.mapper;

import org.mapstruct.Mapper;
import org.thuc.shoppe.entity.ProductImage;
import org.thuc.shoppe.model.dto.ProductImageDto;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {
    ProductImageDto toProductImageDto(ProductImage productImage);
}
