package org.example.backend.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.backend.customStatusCodes.SelectedOrderStatus;
import org.example.backend.dao.OrderDao;
import org.example.backend.dto.OrderStatus;
import org.example.backend.dto.impl.OrderDTO;
import org.example.backend.entity.impl.Order;
import org.example.backend.exception.DataPersistException;
import org.example.backend.service.OrderService;
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
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveOrder(OrderDTO orderDTO) {
        Order order = mapper.toOrderEntity(orderDTO);
        order.setId(generateOrderId());
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
            return new SelectedOrderStatus(1, "Order not found");
        }
        return mapper.toOrderDTO(fetchedOrder);
    }

    @Override
    public String generateOrderId() {
        String jpql = "SELECT o.id FROM Order o ORDER BY o.id DESC LIMIT 1";
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);

        query.setMaxResults(1);
        String maxOrderId = null;
        try {
            maxOrderId = query.getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            return "O00-001";
        }
        int newOrderId = Integer.parseInt(maxOrderId.replace("O00-", "")) + 1;
        return String.format("O00-%03d", newOrderId);
    }
}
