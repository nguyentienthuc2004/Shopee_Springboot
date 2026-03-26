package org.thuc.shoppe.repo;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.thuc.shoppe.entity.Category;

import java.util.List;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.products")
    List<Category> findWithProduct();
}
