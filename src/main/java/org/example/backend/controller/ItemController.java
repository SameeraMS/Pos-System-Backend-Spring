package org.example.backend.controller;

import org.example.backend.customStatusCodes.SelectedItemStatus;
import org.example.backend.dto.ItemStatus;
import org.example.backend.dto.impl.ItemDTO;
import org.example.backend.exception.DataPersistException;
import org.example.backend.exception.ItemNotFoundException;
import org.example.backend.service.ItemService;
import org.example.backend.util.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/nextId", produces = MediaType.APPLICATION_JSON_VALUE)
    public String generateItemId(){
        return itemService.generateItemId();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addItem(@RequestBody ItemDTO itemDto) {
        try{
            itemService.saveItem(itemDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDTO> getAllItems(){
        return itemService.getAllItem();
    }

    @GetMapping(value = "/{propertyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemStatus getItem(@PathVariable("propertyId") String propertyId){
        boolean isItemIdValid = Regex.ITEM_ID_REGEX.matches(propertyId);
        if (isItemIdValid){
            return itemService.getSelectedItem(propertyId);
        }else{
            return new SelectedItemStatus(1, "Item Id Invalid");
        }
    }

    @PutMapping(value = "/{propertyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateItem(@PathVariable("propertyId") String propertyId, @RequestBody ItemDTO itemDto){
        boolean isItemIdValid = Regex.ITEM_ID_REGEX.matches(propertyId);
        try{
            if(isItemIdValid){
                itemService.updateItem(propertyId, itemDto);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (ItemNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{propertyId}")
    public ResponseEntity<Void> deleteItem(@PathVariable("propertyId") String propertyId){
        boolean isItemIdValid = Regex.ITEM_ID_REGEX.matches(propertyId);
        try{
            if(isItemIdValid){
                itemService.deleteItem(propertyId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (ItemNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
