package org.thuc.shoppe.mapper;

import org.mapstruct.Mapper;
import org.thuc.shoppe.entity.ProductVariant;
import org.thuc.shoppe.model.dto.ProductVariantDto;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {
    ProductVariantDto toProductVariantDto(ProductVariant productVariant);
}
