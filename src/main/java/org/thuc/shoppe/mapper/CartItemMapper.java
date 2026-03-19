package org.thuc.shoppe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.thuc.shoppe.entity.CartItem;
import org.thuc.shoppe.model.dto.CartItemDto;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    @Mapping(source = "productVariant.id", target = "productVariantId")
    CartItemDto toCartItemDto(CartItem cartItem);

}