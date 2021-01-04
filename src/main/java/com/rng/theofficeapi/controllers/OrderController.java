package com.rng.theofficeapi.controllers;

import com.rng.theofficeapi.dto.OrderDTO;
import com.rng.theofficeapi.entities.Order;
import com.rng.theofficeapi.entities.Payment;
import com.rng.theofficeapi.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody OrderDTO orderDTO){
        Order order = orderService.fromDTO(orderDTO);
        orderService.save(order);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.getId()).toUri()).build();
    }
}