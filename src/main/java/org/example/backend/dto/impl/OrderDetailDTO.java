package org.example.backend.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.dto.OrderDetailsStatus;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailDTO implements OrderDetailsStatus {
    private String order_id;
    private String item_id;
    private int qty;
    private double unit_price;
    private double total;

}
