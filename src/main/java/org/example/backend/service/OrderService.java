package org.example.backend.service;

import org.example.backend.dto.OrderStatus;
import org.example.backend.dto.impl.OrderDTO;

import java.util.List;

public interface OrderService {
    void saveOrder(OrderDTO orderDTO);
    List<OrderDTO> getAllOrders();
    OrderStatus getSelectedOrder(String orderId);
    String generateOrderId();
}
