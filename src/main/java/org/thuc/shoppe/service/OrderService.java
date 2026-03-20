package org.thuc.shoppe.service;

import org.thuc.shoppe.model.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrderPermissticLock(List<Long> cartItemIds) throws InterruptedException;
    public OrderDto createOrder(List<Long> cartItemIds);
}
