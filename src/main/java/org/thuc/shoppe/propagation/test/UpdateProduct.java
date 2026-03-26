package org.thuc.shoppe.propagation.test;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.thuc.shoppe.entity.ProductVariant;
import org.thuc.shoppe.repo.ProductVariantRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UpdateProduct {
    private final UpdatePrice updatePrice;
    private final UpdateStock updateStock;
    @Lazy
    @Autowired
    private UpdateProduct updateProduct;

    @Transactional
    public void updateProduct(Long id, int quantity, BigDecimal price){
        System.out.println("Update product TX active: "
                + TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("Update product TX name: "
                + TransactionSynchronizationManager.getCurrentTransactionName());
        try{
            updatePrice.updatePrice(id, price);
        }catch (Exception e){
            System.out.println("Update price failed");
        }
        updateProduct.updateStock(id, quantity);


    }

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
