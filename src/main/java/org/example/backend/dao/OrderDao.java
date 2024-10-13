package org.example.backend.dao;

import org.example.backend.entity.impl.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order,String> {
}
