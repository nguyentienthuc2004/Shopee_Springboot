package org.thuc.shoppe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thuc.shoppe.propagation.test.UpdateProduct;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/propagation")
@RequiredArgsConstructor
public class PropagationController {
    private final UpdateProduct updateProduct;
    @GetMapping("/test")
    public void testRequired(@RequestParam Long id, @RequestParam int quantity,@RequestParam BigDecimal price) {
        updateProduct.updateProduct(id,quantity,price);
    }
}
