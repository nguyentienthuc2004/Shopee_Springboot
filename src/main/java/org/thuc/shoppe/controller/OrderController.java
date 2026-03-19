package org.thuc.shoppe.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thuc.shoppe.model.dto.OrderDto;
import org.thuc.shoppe.model.request.cart.CartItemRequest;
import org.thuc.shoppe.repo.OrderRepository;
import org.thuc.shoppe.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Log4j2
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(@RequestBody List<CartItemRequest> cartItems){
        log.debug("Request to create order with cart items: {}", cartItems);
        OrderDto order = orderService.createOrder(cartItems);
        return ResponseEntity.ok(new OrderDto());
    }
}
