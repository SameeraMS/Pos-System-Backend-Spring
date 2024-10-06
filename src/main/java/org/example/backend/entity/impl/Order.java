package org.example.backend.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.entity.SuperEntity;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order implements SuperEntity {
    @Id
    private String id;
    private String date;
    private double discount_value;
    private double sub_total;
    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer_id;
    @OneToMany(mappedBy = "order_id")
    private List<OrderDetail> orderDetails;
}
