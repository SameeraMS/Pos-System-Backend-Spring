package org.example.backend.entity.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.entity.SuperEntity;


import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderIdDetails implements SuperEntity {
    private Long order;
    private Long item;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderIdDetails that = (OrderIdDetails) o;
        return Objects.equals(order, that.order) && Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, item);
    }
}
