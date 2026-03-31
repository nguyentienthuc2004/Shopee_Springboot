package org.thuc.shoppe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thuc.shoppe.entity.TemporaryTable;

public interface MemoryRepository extends JpaRepository<TemporaryTable,Long> {
}
