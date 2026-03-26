package org.thuc.shoppe.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thuc.shoppe.entity.ProductVariant;
import org.thuc.shoppe.model.dto.ProductVariantDto;
import org.thuc.shoppe.model.response.ResponseSuccessDto;
import org.thuc.shoppe.repo.ProductVariantRepository;
import org.thuc.shoppe.service.impl.ProductVariantService;

import java.util.List;

@RestController
@RequestMapping("/api/product-variants")
@Slf4j
@RequiredArgsConstructor
public class ProductVariantController {
    private final ProductVariantService productVariantService;
    @GetMapping

    public Object getAllProductVariants() {
        // This is a placeholder implementation. You should replace it with actual logic to fetch product variants.
        List<ProductVariant> productVariants = productVariantService.findAll();
        return productVariants;
    }
}
