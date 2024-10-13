package org.example.backend.service.impl;

import org.example.backend.dao.ItemDao;
import org.example.backend.dao.OrderDetailsDao;
import org.example.backend.dto.impl.OrderDetailDTO;
import org.example.backend.entity.impl.OrderDetail;
import org.example.backend.exception.DataPersistException;
import org.example.backend.service.OrderDetailsService;
import org.example.backend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
@Transactional
public class OrderDetailServiceIMPL implements OrderDetailsService{
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private OrderDetailsDao orderDetailsDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetail savedOrderDetail = orderDetailsDao.save(mapping.toOrderDetailEntity(orderDetailDTO));
        if (savedOrderDetail == null) {
            throw new DataPersistException("Order Detail not saved");
        }


    }

    @Override
    public List<OrderDetailDTO> getOrderDetails(String orderId) {
        List<OrderDetail> allDetails = orderDetailsDao.findAllById(Collections.singleton(orderId));
        List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();

        allDetails.forEach(orderDetail -> {
            orderDetailDTOS.add(mapping.toOrderDetailDTO(orderDetail));
        });
        return orderDetailDTOS;
    }
}
