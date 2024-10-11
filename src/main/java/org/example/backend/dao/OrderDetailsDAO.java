package org.example.backend.dao;

import org.example.backend.entity.impl.OrderDetail;
import org.example.backend.entity.impl.OrderIdDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsDAO extends JpaRepository<OrderDetail, OrderIdDetails> {
}

