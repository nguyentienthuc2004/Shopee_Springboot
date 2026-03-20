package org.thuc.shoppe.service;

import org.thuc.shoppe.model.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrderPermissticLock(List<Long> cartItemIds) throws InterruptedException;
    OrderDto createOrder(List<Long> cartItemIds) throws InterruptedException;
    OrderDto createOrderOptimisticLock(List<Long> cartItemIds) throws InterruptedException;
}
