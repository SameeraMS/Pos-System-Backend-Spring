package org.example.backend.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.entity.SuperEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "order_details")
@IdClass(OrderIdDetails.class)
public class OrderDetail implements SuperEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "item_id",nullable = false)
    private Item item;
    private int qty;
    private double unit_price;
    private double total;

}