package org.example.backend.service.impl;

import org.example.backend.dao.ItemDao;
import org.example.backend.dao.OrderDetailsDao;
import org.example.backend.dto.impl.OrderDetailDTO;
import org.example.backend.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrderDetailServiceIMPL implements OrderDetailsService{
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private OrderDetailsDao orderDetailsDao;
    @Override
    public void saveOrderDetail(OrderDetailDTO orderDetailDTO) {

    }

    @Override
    public List<OrderDetailDTO> getOrderDetails(String orderId) {
        return List.of();
    }
}
