package org.example.backend.service;

import org.example.backend.dto.ItemStatus;
import org.example.backend.dto.impl.ItemDTO;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    void updateItem(String id, ItemDTO itemDTO);
    void deleteItem(String id);
    ItemStatus getSelectedItem(String id);
    List<ItemDTO> getAllItem();
    String generateItemId();
    List<ItemDTO> searchByItemCode(String newItemCode);
}
