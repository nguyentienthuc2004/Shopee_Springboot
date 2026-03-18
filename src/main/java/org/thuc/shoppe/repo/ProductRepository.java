package org.thuc.shoppe.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.thuc.shoppe.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategoryId(Long categoryId);

    @Query(
            value = """
        SELECT p.* 
        FROM products p 
        JOIN categories c ON p.category_id = c.id
        WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """,
            countQuery = """
        SELECT COUNT(*) 
        FROM products p 
        JOIN categories c ON p.category_id = c.id
        WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """,
            nativeQuery = true
    )
    Page<Product> searchByProductNameOrCategoryName(@Param("keyword") String keyword, Pageable pageable);
}
