package org.thuc.shoppe.proxy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thuc.shoppe.entity.ProductVariant;
import org.thuc.shoppe.repo.ProductVariantRepository;

@Service
@RequiredArgsConstructor
public class ProductVariantServiceProxy {
    private final ProductVariantRepository productVariantRepository;

    @Transactional
    public void updateStock(Long productVariantId, int newStock) throws NoSuchMethodException {
        ProductVariant productVariant = productVariantRepository.findById(productVariantId)
                .orElseThrow(() -> new RuntimeException("Product variant not found: " + productVariantId));
        productVariant.setStock(newStock);
        productVariantRepository.save(productVariant);
    }
}
