package org.thuc.shoppe.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.thuc.shoppe.entity.Color;
import org.thuc.shoppe.entity.Product;
import org.thuc.shoppe.entity.ProductImage;
import org.thuc.shoppe.entity.Size;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductVariantDto {

    private Long id;

    private BigDecimal price;

    private Integer stock;

    private ProductImageDto  productImageDto;
}
