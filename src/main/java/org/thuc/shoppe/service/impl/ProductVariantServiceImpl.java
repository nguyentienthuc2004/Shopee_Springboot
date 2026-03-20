package org.thuc.shoppe.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.thuc.shoppe.entity.ProductVariant;
import org.thuc.shoppe.exception.NotFoundException;
import org.thuc.shoppe.mapper.ProductMapper;
import org.thuc.shoppe.mapper.ProductVariantMapper;
import org.thuc.shoppe.model.dto.ProductVariantDto;
import org.thuc.shoppe.repo.ProductVariantRepository;
import org.thuc.shoppe.service.ProductVariantService;
@Service
@RequiredArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {
    private final ProductVariantRepository productVariantRepository;
    private final ProductVariantMapper productVariantMapper;
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void updateProductVariantStock(Long productVariantId, int newStock) throws InterruptedException {
        ProductVariant productVariant = productVariantRepository.findById(productVariantId)
                .orElseThrow(() -> new NotFoundException("Product variant not found: " + productVariantId));
        productVariant.setStock(newStock);
        productVariantRepository.save(productVariant);
        entityManager.flush(); // Ensure changes are sent to the database immediately
        System.out.println("Transaction A: Updated stock to " + newStock);
        Thread.sleep(5000);
        System.out.println("Transaction A: Rollback transaction");
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // Rollback the transaction to simulate uncommitted changes
    }
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void readProductVariantStock(Long productVariantId) {
        ProductVariant productVariant = productVariantRepository.findById(productVariantId)
                .orElseThrow(() -> new NotFoundException("Product variant not found: " + productVariantId));
        System.out.println("Transaction B: Read stock as " + productVariant.getStock());
    }
    @Transactional(readOnly = true)
    @Override
    public ProductVariantDto updateProductVariantStockWithReadOnlyFalse(Long productVariantId, int newStock) {
        ProductVariant productVariant = productVariantRepository.findById(productVariantId)
                .orElseThrow(() -> new NotFoundException("Product variant not found: " + productVariantId));
        productVariant.setStock(newStock);
        entityManager.clear();
        productVariant = productVariantRepository.findById(productVariantId)
                .orElseThrow(() -> new NotFoundException("Product variant not found: " + productVariantId));
        return productVariantMapper.toProductVariantDto(productVariant);
    }
}
