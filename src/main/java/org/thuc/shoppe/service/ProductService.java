package org.thuc.shoppe.service;

import org.thuc.shoppe.model.dto.PageResponseDto;
import org.thuc.shoppe.model.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long productId);

    List<ProductDto> getProductsByCategoryId(Long categoryId);

    PageResponseDto<List<ProductDto>> searchProducts(String keyword, int pageNo, int pageSize);
}
