package org.example.backend.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/health")
public class HealthController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String heathTest(){
        return "Pos System is working";
    }
}
