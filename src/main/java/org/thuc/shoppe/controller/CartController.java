package org.thuc.shoppe.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thuc.shoppe.model.dto.CartItemDto;
import org.thuc.shoppe.model.dto.CartResponseDto;
import org.thuc.shoppe.model.request.cart.CartItemRequest;
import org.thuc.shoppe.model.response.ResponseSuccessDto;
import org.thuc.shoppe.service.CartService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
    public final Logger log = LoggerFactory.getLogger(CartController.class);
    public final CartService cartService;

    @PostMapping("create")
    public ResponseEntity<ResponseSuccessDto<CartResponseDto>> createCart(){
        log.debug("Request to create cart");
        CartResponseDto cart = cartService.createCart();
        return ResponseEntity.ok(ResponseSuccessDto.success(cart));
    }
    @PostMapping("add")
    public ResponseEntity<ResponseSuccessDto<List<CartItemDto>>> addToCart(@RequestBody CartItemRequest cartItemRequest){
        log.debug("Request to add product to cart {}", cartItemRequest);
        return ResponseEntity.ok(ResponseSuccessDto.success(cartService.addItem(cartItemRequest)));
    }
}
