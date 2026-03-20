package org.thuc.shoppe.model.request.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItemRequest {
    private Long productVariantId;
    private Integer quantity;
}
