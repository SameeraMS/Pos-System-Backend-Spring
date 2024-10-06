package org.example.backend.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.entity.SuperEntity;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "order_details")
public class OrderDetail implements SuperEntity {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order_id;
    @ManyToOne
    @JoinColumn(name = "item_id",nullable = false)
    private Item item_id;
    private int qty;
    private double unit_price;
    private double total;

}