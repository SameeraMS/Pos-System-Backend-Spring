package org.example.backend.util;

import org.example.backend.dto.impl.CustomerDTO;
import org.example.backend.dto.impl.ItemDTO;
import org.example.backend.dto.impl.OrderDTO;
import org.example.backend.dto.impl.OrderDetailDTO;
import org.example.backend.entity.impl.Customer;
import org.example.backend.entity.impl.Item;
import org.example.backend.entity.impl.Order;
import org.example.backend.entity.impl.OrderDetail;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    //for customer mapping
    public Customer toCustomerEntity(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, Customer.class);
    }

    public CustomerDTO toCustomerDTO(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public List<CustomerDTO> toCustomerDTOList(List<Customer> customers) {
        return customers.stream().map(this::toCustomerDTO).toList();
    }


    //for item mapping
    public Item toItemEntity(ItemDTO itemDTO) {
        return modelMapper.map(itemDTO, Item.class);
    }

    public ItemDTO toItemDTO(Item item) {
        return modelMapper.map(item, ItemDTO.class);
    }

    public List<ItemDTO> toItemDTOList(List<Item> items) {
        return items.stream().map(this::toItemDTO).toList();
    }

    //for order mapping
    public Order toOrderEntity(OrderDTO orderDTO) {
        return new Order(orderDTO.getId(), orderDTO.getDate(), orderDTO.getDiscount_value(), orderDTO.getSub_total(), null,null);
    }

    public OrderDTO toOrderDTO(Order order) {
        return new OrderDTO(order.getId(), order.getDate(), order.getDiscount_value(), order.getSub_total(), order.getCustomer().getId());
    }

    public List<OrderDTO> toOrderDTOList(List<Order> orders) {
        return orders.stream().map(this::toOrderDTO).toList();
    }


    //for order detail mapping
    public OrderDetail toOrderDetailEntity(OrderDetailDTO orderDetailDTO) {
        return new OrderDetail(null, null, orderDetailDTO.getQty(), orderDetailDTO.getUnit_price(), orderDetailDTO.getTotal());
    }

    public OrderDetailDTO toOrderDetailDTO(OrderDetail orderDetail) {
        return new OrderDetailDTO(orderDetail.getOrder().getId(), orderDetail.getItem().getId(), orderDetail.getQty(), orderDetail.getUnit_price(), orderDetail.getTotal());
    }

    public List<OrderDetailDTO> toOrderDetailDTOList(List<OrderDetail> orderDetails) {
        return orderDetails.stream().map(this::toOrderDetailDTO).toList();
    }

}
