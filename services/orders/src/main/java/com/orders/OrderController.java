package com.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok().body(orderService.getOrders());
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok().body(orderService.getOrdersById(id));
    }

    @GetMapping("/orders/user/{id}")
    public ResponseEntity<List<Order>> getOrdersByUserid(@PathVariable Long id) {
        return ResponseEntity.ok().body(orderService.getOrdersByUserId(id));
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody AddOrderDto addOrderDto) {
        return ResponseEntity.status(201).body(orderService.addOrder(addOrderDto));
    }
}
