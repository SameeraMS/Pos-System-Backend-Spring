package org.example.backend.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.backend.dao.ItemDao;
import org.example.backend.dao.OrderDetailsDao;
import org.example.backend.dto.impl.OrderDetailDTO;
import org.example.backend.entity.impl.Item;
import org.example.backend.entity.impl.OrderDetail;
import org.example.backend.exception.DataPersistException;
import org.example.backend.service.OrderDetailsService;
import org.example.backend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetail savedOrderDetail = orderDetailsDao.save(mapping.toOrderDetailEntity(orderDetailDTO));

        String itemId = orderDetailDTO.getItem_id();
        int qty = orderDetailDTO.getQty();

        Item fetcheItem = itemDao.getReferenceById(itemId);
        fetcheItem.setQty(fetcheItem.getQty() - qty);

        Item savedItem = itemDao.save(fetcheItem);

        if (savedOrderDetail == null || savedItem == null) {
            throw new DataPersistException("Order Detail not saved");
        }
    }

    @Override
    public List<OrderDetailDTO> getOrderDetails(String orderId) {
        String jpql = "SELECT od FROM OrderDetail od WHERE od.order.id = :orderId";

        TypedQuery<OrderDetail> query = entityManager.createQuery(jpql, OrderDetail.class);
        query.setParameter("orderId", orderId);

        List<OrderDetail> orderDetails = query.getResultList();

        List<OrderDetailDTO> orderDetailDTOs = new ArrayList<>();
        for (OrderDetail od : orderDetails) {
            orderDetailDTOs.add(new OrderDetailDTO(od.getOrder().getId(), od.getItem().getId(), od.getQty(), od.getUnit_price(), od.getTotal()));
        }
        return orderDetailDTOs;
    }
}
