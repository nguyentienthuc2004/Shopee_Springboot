package org.thuc.shoppe.propagation.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.thuc.shoppe.entity.ProductVariant;
import org.thuc.shoppe.repo.ProductVariantRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateStock {
    private final ProductVariantRepository productVariantRepository;
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateStock(Long id, int quantity){
        System.out.println("Update stock TX activate: "
                + TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("Update stock TX name: "
                + TransactionSynchronizationManager.getCurrentTransactionName());
        ProductVariant productVariant = productVariantRepository.findById(id).orElseThrow();
        productVariant.setStock(quantity);
    }
}
