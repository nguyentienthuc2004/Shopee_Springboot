package org.thuc.shoppe.model.dto;

import jakarta.persistence.*;
import org.thuc.shoppe.entity.Color;
import org.thuc.shoppe.entity.Product;
import org.thuc.shoppe.entity.ProductImage;
import org.thuc.shoppe.entity.Size;

import java.math.BigDecimal;

public class ProductVariantDto {

    private Long id;

    private Color color;

    private Size size;

    private BigDecimal price;

    private Integer stock;

    private ProductImageDto  productImageDto;
}
