package org.example.backend.controller;

import org.example.backend.dto.impl.OrderDetailDTO;
import org.example.backend.exception.DataPersistException;
import org.example.backend.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/orderDetails")
public class OrderDetailsController {
    @Autowired
    private OrderDetailsService orderDetailService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addOrderDetail(@RequestBody OrderDetailDTO orderDetailDto) {
        try {
            orderDetailService.saveOrderDetail(orderDetailDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{propertyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDetailDTO> getOrderDetail(@PathVariable("propertyId") String propertyId) {
        return orderDetailService.getOrderDetails(propertyId);
    }
}
