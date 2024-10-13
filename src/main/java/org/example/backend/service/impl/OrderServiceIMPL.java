package org.example.backend.service.impl;

import org.example.backend.customStatusCodes.SelectedOrderStatus;
import org.example.backend.dao.ItemDao;
import org.example.backend.dao.OrderDao;
import org.example.backend.dao.OrderDetailsDao;
import org.example.backend.dto.OrderStatus;
import org.example.backend.dto.impl.OrderDTO;
import org.example.backend.entity.impl.Order;
import org.example.backend.exception.DataPersistException;
import org.example.backend.service.OrderService;
import org.example.backend.util.AppUtil;
import org.example.backend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class OrderServiceIMPL implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private Mapping mapper;

    @Override
    public void saveOrder(OrderDTO orderDTO) {
        Order order = mapper.toOrderEntity(orderDTO);
        order.setId(AppUtil.generateOrderId());
        Order savedOrder = orderDao.save(order);
        if (savedOrder == null) {
            throw new DataPersistException("Failed to add order");
        }
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> placeOrders = orderDao.findAll();
        List<OrderDTO> placeOrderDtos = new ArrayList<>();

        placeOrders.forEach(placeOrder -> {
            placeOrderDtos.add(mapper.toOrderDTO(placeOrder));
        });
        return placeOrderDtos;
    }

    @Override
    public OrderStatus getSelectedOrder(String orderId) {
        Order fetchedOrder = orderDao.getReferenceById(orderId);
        if (fetchedOrder == null) {
            return new SelectedOrderStatus(1,"Order not found");
        }
        return mapper.toOrderDTO(fetchedOrder);
    }
}
