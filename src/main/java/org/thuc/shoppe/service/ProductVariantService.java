package org.thuc.shoppe.service;

import org.thuc.shoppe.model.dto.ProductVariantDto;

public interface ProductVariantService {
    public void updateProductVariantStock(Long productVariantId, int newStock) throws InterruptedException;
    public void readProductVariantStock(Long productVariantId);
    public ProductVariantDto updateProductVariantStockWithReadOnlyFalse(Long productVariantId, int newStock);
}
