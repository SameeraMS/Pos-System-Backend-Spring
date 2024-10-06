package org.example.backend.util;

import org.example.backend.dto.impl.CustomerDTO;
import org.example.backend.dto.impl.ItemDTO;
import org.example.backend.dto.impl.OrderDTO;
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
        return modelMapper.map(orderDTO, Order.class);
    }

    public OrderDTO toOrderDTO(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }

    public List<OrderDTO> toOrderDTOList(List<Order> orders) {
        return orders.stream().map(this::toOrderDTO).toList();
    }


    //for order detail mapping
    public OrderDetail toOrderDetailEntity(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, OrderDetail.class);
    }

    public OrderDTO toOrderDetailDTO(OrderDetail orderDetail) {
        return modelMapper.map(orderDetail, OrderDTO.class);
    }

    public List<OrderDTO> toOrderDetailDTOList(List<OrderDetail> orderDetails) {
        return orderDetails.stream().map(this::toOrderDetailDTO).toList();
    }

}
