package org.thuc.shoppe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thuc.shoppe.entity.ProductVariant;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant,Long> {
}
