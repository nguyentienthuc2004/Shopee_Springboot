package org.thuc.shoppe.service;

import org.thuc.shoppe.model.dto.CartResponseDto;
import org.thuc.shoppe.model.request.cart.CartItemRequest;

public interface CartService {
    CartResponseDto createCart();

    void addItem(CartItemRequest cartItemRequest);
}
