package org.example.backend.service;

import org.example.backend.dto.impl.OrderDetailDTO;

import java.util.List;

public interface OrderDetailsService {
    void saveOrderDetail(OrderDetailDTO orderDetailDTO);
    List<OrderDetailDTO> getOrderDetails(String orderId);
}
