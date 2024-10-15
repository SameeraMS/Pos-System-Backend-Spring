package org.example.backend.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.backend.customStatusCodes.SelectedOrderStatus;
import org.example.backend.dao.CustomerDao;
import org.example.backend.dao.OrderDao;
import org.example.backend.dto.OrderStatus;
import org.example.backend.dto.impl.OrderDTO;
import org.example.backend.entity.impl.Customer;
import org.example.backend.entity.impl.Order;
import org.example.backend.exception.DataPersistException;
import org.example.backend.service.OrderService;
import org.example.backend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private Mapping mapper;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveOrder(OrderDTO orderDTO) {
        Optional<Customer> customer = customerDao.findById(orderDTO.getCustomer_id());
        Order order = mapper.toOrderEntity(orderDTO);
        order.setId(generateOrderId());
        order.setCustomer(customer.get());
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

    @Override
    public List<OrderDTO> searchByOrderId(String id) {
        String jpql = "SELECT o FROM Order o WHERE o.id LIKE :orderId";

        TypedQuery<Order> query = entityManager.createQuery(jpql, Order.class);

        query.setParameter("orderId", id + "%");

        List<Order> orders = query.getResultList();
        List<OrderDTO> orderDTOS = new ArrayList<>();

        orders.forEach(order -> {
            orderDTOS.add(mapper.toOrderDTO(order));
        });
        return orderDTOS;
    }

}
