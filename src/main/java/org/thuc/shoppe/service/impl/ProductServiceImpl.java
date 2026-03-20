package org.thuc.shoppe.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thuc.shoppe.constant.ApiMessages;
import org.thuc.shoppe.entity.Product;
import org.thuc.shoppe.mapper.ProductMapper;
import org.thuc.shoppe.model.dto.PageResponseDto;
import org.thuc.shoppe.model.dto.ProductDto;
import org.thuc.shoppe.repo.ProductRepository;
import org.thuc.shoppe.service.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::toProductDto).toList();
    }

    @Override
    public ProductDto getProductById(Long productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductDto)
                .orElseThrow(() -> new RuntimeException(ApiMessages.PRODUCT_NOT_FOUND + productId));
    }
    @Override
    public List<ProductDto> getProductsByCategoryId(Long categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        return products.stream().map(productMapper::toProductDto).toList();
    }

    @Override
    public PageResponseDto<List<ProductDto>> searchProducts(String keyword, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        Page<Product> products = productRepository.searchByProductNameOrCategoryName(keyword, pageable);
        return PageResponseDto.<List<ProductDto>>builder()
                .content(products.getContent()
                        .stream()
                        .map(productMapper::toProductDto)
                        .toList())
                .page(pageNo)
                .pageSize(pageSize)
                .totalElements(products.getTotalElements())
                .totalPages(products.getTotalPages())
                .build();
    }

    @Override
    public List<ProductDto> getProductsByStockRange(int stockMin, int stockMax) {
        List<Product> products = productRepository.findByStockBetween(stockMin, stockMax);
        return products.stream().map(productMapper::toProductDto).toList();
    }
}
