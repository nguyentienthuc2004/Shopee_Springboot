package org.thuc.shoppe.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thuc.shoppe.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
