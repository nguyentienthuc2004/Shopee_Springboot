package org.thuc.shoppe.service;

public interface ProductVariantService {
    public void updateProductVariantStock(Long productVariantId, int newStock) throws InterruptedException;
    public void readProductVariantStock(Long productVariantId);
}
