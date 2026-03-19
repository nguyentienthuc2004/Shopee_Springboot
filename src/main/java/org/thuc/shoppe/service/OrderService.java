package org.thuc.shoppe.service;

import org.thuc.shoppe.model.dto.OrderDto;
import org.thuc.shoppe.model.request.cart.CartItemRequest;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(List<CartItemRequest> cartItems);
}
