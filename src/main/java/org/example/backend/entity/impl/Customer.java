package org.example.backend.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.entity.SuperEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class Customer implements SuperEntity {
    @Id
    private String id;
    private String name;
    private String address;
    @Column(name = "contact", unique = true)
    private String contact;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
