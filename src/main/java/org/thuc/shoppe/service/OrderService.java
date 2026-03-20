package org.thuc.shoppe.service;

import org.thuc.shoppe.model.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(List<Long> cartItems);
}
