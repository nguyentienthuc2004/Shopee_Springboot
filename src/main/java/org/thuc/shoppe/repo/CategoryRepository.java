package org.thuc.shoppe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thuc.shoppe.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
