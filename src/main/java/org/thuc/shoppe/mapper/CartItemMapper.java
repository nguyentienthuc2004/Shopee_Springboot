package org.thuc.shoppe.mapper;

import org.mapstruct.Mapper;
import org.thuc.shoppe.entity.CartItem;
import org.thuc.shoppe.model.dto.CartItemResponseDto;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemResponseDto toCartItemResponseDto(CartItem cartItem);
}
