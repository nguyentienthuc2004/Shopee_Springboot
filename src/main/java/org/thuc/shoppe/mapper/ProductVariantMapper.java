package org.thuc.shoppe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.thuc.shoppe.entity.ProductVariant;
import org.thuc.shoppe.model.dto.ProductVariantDto;

@Mapper(componentModel = "spring", uses = {ProductImageMapper.class})
public interface ProductVariantMapper {

    @Mapping(source = "productImage", target = "productImageDto")
    ProductVariantDto toProductVariantDto(ProductVariant productVariant);
}
