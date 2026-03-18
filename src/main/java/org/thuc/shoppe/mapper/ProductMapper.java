package org.thuc.shoppe.mapper;

import org.mapstruct.Mapper;
import org.thuc.shoppe.entity.Product;
import org.thuc.shoppe.model.dto.ProductDto;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toProductDto(Product product);
}
