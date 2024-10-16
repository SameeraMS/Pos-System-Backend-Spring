package org.example.backend.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.dto.ItemStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO implements ItemStatus {
    private String id;
    private String description;
    private double unitPrice;
    private int qty;
}
