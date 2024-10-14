package org.example.backend.controller;

import org.example.backend.dao.OrderDetailsDao;
import org.example.backend.dto.impl.OrderDetailDTO;
import org.example.backend.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/health")
public class HealthController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String heathTest(){
        return "Pos System is working";
    }

    @Autowired
    private OrderDetailsService orderDetailsService;

    @GetMapping(value = "/db" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDetailDTO> getOrderDetails(){
        List<OrderDetailDTO> orderDetails = orderDetailsService.getOrderDetails("OID-001");
        return orderDetails;
    }
}
