package org.thuc.shoppe.service;

import org.thuc.shoppe.model.dto.CartItemDto;
import org.thuc.shoppe.model.dto.CartResponseDto;
import org.thuc.shoppe.model.request.cart.CartItemRequest;

import java.util.List;

public interface CartService {
    CartResponseDto createCart();

    List<CartItemDto> addItem(CartItemRequest cartItemRequest);
}
