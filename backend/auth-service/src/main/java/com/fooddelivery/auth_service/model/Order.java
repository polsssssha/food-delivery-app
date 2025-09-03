package com.fooddelivery.auth_service.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    private String status; // CREATED, COOKING, DELIVERING, COMPLETED, CANCELLED

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TEXT")
    private String items;
}
