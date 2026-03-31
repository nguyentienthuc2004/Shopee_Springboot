package org.thuc.shoppe.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Fallback;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thuc.shoppe.model.dto.PageResponseDto;
import org.thuc.shoppe.model.dto.ProductDto;
import org.thuc.shoppe.model.response.ResponseSuccessDto;
import org.thuc.shoppe.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ResponseSuccessDto<List<ProductDto>>> getAllProducts(){
        log.debug("Request to get all products");
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(ResponseSuccessDto.success(products));
    }
    @GetMapping("{productId}")
    public ResponseEntity<ResponseSuccessDto<ProductDto>> getProductById(@PathVariable Long productId){
        log.debug("Request to get product by id: {}", productId);
        ProductDto product = productService.getProductById(productId);
        return ResponseEntity.ok(ResponseSuccessDto.success(product));
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ResponseSuccessDto<List<ProductDto>>> getProductsByCategoryId(@PathVariable Long categoryId){
        log.debug("Request to get products by category id: {}", categoryId);
        List<ProductDto> products = productService.getProductsByCategoryId(categoryId);
        return ResponseEntity.ok(ResponseSuccessDto.success(products));
    }
    @GetMapping("search")
    public ResponseEntity<ResponseSuccessDto<PageResponseDto<List<ProductDto>>>> searchProducts(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        log.debug("Request to search products with keyword: {}, page: {}, pageSize: {}", keyword, pageNo, pageSize);
        PageResponseDto<List<ProductDto>> result = productService.searchProducts(keyword, pageNo, pageSize);
        return ResponseEntity.ok(ResponseSuccessDto.success(result));
    }
    @GetMapping("filter-stock")
    public ResponseEntity<ResponseSuccessDto<List<ProductDto>>> getProductsByStock(
            @RequestParam int stockMin,
            @RequestParam int stockMax) {
        log.debug("Request to filter products by stock range: {} - {}", stockMin, stockMax);
        List<ProductDto> products = productService.getProductsByStockRange(stockMin, stockMax);
        return ResponseEntity.ok(ResponseSuccessDto.success(products));
    }
}
