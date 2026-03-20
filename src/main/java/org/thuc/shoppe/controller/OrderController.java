package org.thuc.shoppe.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thuc.shoppe.model.dto.OrderDto;
import org.thuc.shoppe.model.request.order.OrderRequestDto;
import org.thuc.shoppe.model.response.ResponseSuccessDto;
import org.thuc.shoppe.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Log4j2
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/create")
    public ResponseEntity<ResponseSuccessDto<OrderDto>> createOrder(@RequestBody OrderRequestDto orderRequest){
        log.debug("Request to create order with cart items: {}", orderRequest);
        OrderDto order = orderService.createOrder(orderRequest.getCartItemIds());
        return ResponseEntity.ok(ResponseSuccessDto.success(order));
    }
}
