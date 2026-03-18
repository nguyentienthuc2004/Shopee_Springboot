package org.thuc.shoppe.mapper;

import org.mapstruct.Mapper;
import org.thuc.shoppe.entity.Cart;
import org.thuc.shoppe.model.dto.CartResponseDto;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartResponseDto toCartResponseDto(Cart cart);
}
