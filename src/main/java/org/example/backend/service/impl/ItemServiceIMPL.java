package org.example.backend.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.backend.customStatusCodes.SelectedItemStatus;
import org.example.backend.dao.ItemDao;
import org.example.backend.dto.ItemStatus;
import org.example.backend.dto.impl.ItemDTO;
import org.example.backend.entity.impl.Item;
import org.example.backend.exception.DataPersistException;
import org.example.backend.exception.ItemNotFoundException;
import org.example.backend.service.ItemService;
import org.example.backend.util.AppUtil;
import org.example.backend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemServiceIMPL implements ItemService {
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private Mapping mapper;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveItem(ItemDTO itemDTO) {
        itemDTO.setId(AppUtil.generateItemId());
        Item savedItem = itemDao.save(mapper.toItemEntity(itemDTO));
        if (savedItem == null) {
            throw new DataPersistException("Failed to add item");
        }
    }

    @Override
    public void updateItem(String id, ItemDTO itemDTO) {
        Item fetchedItem = itemDao.getReferenceById(id);
        if (fetchedItem == null) {
            throw new ItemNotFoundException("Item not found");
        }
        fetchedItem.setDescription(itemDTO.getDescription());
        fetchedItem.setUnitPrice(itemDTO.getUnitPrice());
        fetchedItem.setQtyOnHand(itemDTO.getQty());
        itemDao.save(fetchedItem);
    }

    @Override
    public void deleteItem(String id) {
        Item fetchedItem = itemDao.getReferenceById(id);
        if (fetchedItem == null) {
            throw new ItemNotFoundException("Item not found");
        }
        itemDao.delete(fetchedItem);
    }

    @Override
    public ItemStatus getSelectedItem(String id) {
        Item fetchedItem = itemDao.getReferenceById(id);
        if (fetchedItem == null) {
            return new SelectedItemStatus(1,"Item not found");
        }
        return mapper.toItemDTO(fetchedItem);
    }

    @Override
    public List<ItemDTO> getAllItem() {
        return mapper.toItemDTOList(itemDao.findAll());
    }

    public String generateItemId() {
        String jpql = "SELECT i.id FROM Item i ORDER BY i.id DESC LIMIT 1";
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
        String maxItemId = query.getSingleResult();
        if (maxItemId != null) {
            int newItemId = Integer.parseInt(maxItemId.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }
}
