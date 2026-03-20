package org.thuc.shoppe.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thuc.shoppe.model.dto.ProductVariantDto;
import org.thuc.shoppe.model.response.ResponseSuccessDto;
import org.thuc.shoppe.service.ProductVariantService;
import org.thuc.shoppe.test_read_uncommited.ReadUncommitted;

@RestController
@RequestMapping("/api/isolation")
@RequiredArgsConstructor
@Slf4j
public class IsolationController {
    private final ReadUncommitted readUncommitted;
    private final ProductVariantService productVariantService;
    @PatchMapping("/{productVariantId}/read-uncommitted")
    public ResponseEntity<ResponseSuccessDto<ProductVariantDto>> readUncommitted(
            @PathVariable Long productVariantId, @RequestParam("stock") int stock
    ) {
        log.debug("Request to read product variant with ID {} using READ UNCOMMITTED isolation level", productVariantId);
        ProductVariantDto productVariant = readUncommitted.testReadUncommitted(productVariantId,stock);
        return ResponseEntity.ok(ResponseSuccessDto.success(productVariant));
    }
    @PatchMapping("/{productVariantId}/read-only")
    public ResponseEntity<ResponseSuccessDto<ProductVariantDto>> testReadOnly(
            @PathVariable Long productVariantId, @RequestParam("stock") int stock){
        log.debug("Request to read product variant with ID {} using READ UNCOMMITTED isolation level and readOnly=false", productVariantId);
        ProductVariantDto productVariantDto=productVariantService.updateProductVariantStockWithReadOnlyFalse(productVariantId,stock);
        return ResponseEntity.ok(ResponseSuccessDto.success(productVariantDto));
    }
}
