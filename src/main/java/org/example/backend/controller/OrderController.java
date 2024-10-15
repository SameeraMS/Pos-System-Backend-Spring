package org.example.backend.controller;

import org.example.backend.dto.impl.OrderDTO;
import org.example.backend.exception.DataPersistException;
import org.example.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/nextId")
    public String generateOrderId() {
        return orderService.generateOrderId();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping(value = "/{propertyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDTO> getOrder(@PathVariable("propertyId") String propertyId) {
        return orderService.searchByOrderId(propertyId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrder(@RequestBody OrderDTO orderDTO) {
       try {
           orderService.saveOrder(orderDTO);
           return new ResponseEntity<>(HttpStatus.CREATED);
       } catch (DataPersistException e) {
           e.printStackTrace();
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       } catch (Exception e) {
           e.printStackTrace();
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }

    }
}
