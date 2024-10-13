package org.example.backend.dao;


import org.example.backend.entity.impl.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDao extends JpaRepository<Item,String> {
}
