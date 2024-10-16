package org.example.backend.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.dto.OrderStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO implements OrderStatus {
    private String id;
    private String date;
    private double discount_value;
    private double sub_total;
    private String customer_id;
}
