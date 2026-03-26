package org.thuc.shoppe.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thuc.shoppe.entity.ProductVariant;
import org.thuc.shoppe.repo.ProductVariantRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductVariantService {
    private final ProductVariantRepository productVariantRepository;

    @Transactional
    public List<ProductVariant> findAll() {
        // This is a placeholder implementation. You should replace it with actual logic to fetch product variants.
        List<ProductVariant> a = productVariantRepository.findAll();

        return  a;
    }
}
