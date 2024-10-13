package org.example.backend.customStatusCodes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.dto.ItemStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SelectedItemStatus implements ItemStatus {
    private int statusCode;
    private String message;
}
