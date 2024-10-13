package org.example.backend.customStatusCodes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.dto.OrderStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SelectedOrderStatus implements OrderStatus {
    private int statusCode;
    private String message;
}
