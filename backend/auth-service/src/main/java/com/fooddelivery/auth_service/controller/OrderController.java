package com.fooddelivery.auth_service.controller;

import com.fooddelivery.auth_service.model.Order;
import com.fooddelivery.auth_service.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // Создание нового заказа
    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        try {
            order.setCreatedAt(LocalDateTime.now());
            order.setStatus("CREATED");
            orderRepository.save(order);
            return ResponseEntity.ok("Order created successfully with ID: " + order.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating order: " + e.getMessage());
        }
    }

    // Получение всех заказов пользователя
    @GetMapping("/user/{userId}")
    public List<Order> getUserOrders(@PathVariable Long userId) {
        return orderRepository.findByUserId(userId);
    }

    // Получение заказа по ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Обновление статуса заказа
    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long id, @RequestBody String status) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setStatus(status);
                    orderRepository.save(order);
                    return ResponseEntity.ok("Order status updated to: " + status);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Получение заказов по статусу
    @GetMapping("/status/{status}")
    public List<Order> getOrdersByStatus(@PathVariable String status) {
        return orderRepository.findByStatus(status);
    }
}