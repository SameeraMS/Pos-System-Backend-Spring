package org.example.backend.customStatusCodes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.dto.CustomerStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SelectedCustomerStatus implements CustomerStatus {
    private int statusCode;
    private String message;
}
