package org.thuc.shoppe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thuc.shoppe.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
