package org.thuc.shoppe.test_isolation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thuc.shoppe.entity.ProductVariant;
import org.thuc.shoppe.exception.NotFoundException;
import org.thuc.shoppe.mapper.ProductVariantMapper;
import org.thuc.shoppe.model.dto.ProductVariantDto;
import org.thuc.shoppe.repo.ProductVariantRepository;
import org.thuc.shoppe.service.ProductVariantService;

@RequiredArgsConstructor
@Component
public class TestIsolation {
    private final ProductVariantService productVariantService;
    private final ProductVariantRepository productVariantRepository;
    private final ProductVariantMapper productVariantMapper;
    public ProductVariantDto testIsolation(Long productVariantId, int stock) {
        Thread A = new Thread(() -> {
            try {
                productVariantService.updateProductVariantStock(productVariantId, stock);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread B = new Thread(() -> {
            try {
                Thread.sleep(2000); // Simulate a short delay before reading
                productVariantService.fetchProductVariantStock(productVariantId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        A.start();
        B.start();

        try {
            A.join();
            B.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ProductVariant productVariant = productVariantRepository.findById(productVariantId)
                .orElseThrow(() -> new NotFoundException("Product variant not found: " + productVariantId));
        ProductVariantDto productVariantDto= productVariantMapper.toProductVariantDto(productVariant);
        return productVariantDto;
    }
}
