package org.thuc.shoppe.propagation.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.thuc.shoppe.repo.ProductVariantRepository;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdatePrice {
    private final ProductVariantRepository productVariantRepository;
    @Transactional
    public void updatePrice(Long id, BigDecimal price){
        System.out.println("Update price TX activate: "
                + TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("Update price TX name: "
                + TransactionSynchronizationManager.getCurrentTransactionName());
        var productVariant = productVariantRepository.findById(id).orElseThrow();
        productVariant.setPrice(price);
    }

}
