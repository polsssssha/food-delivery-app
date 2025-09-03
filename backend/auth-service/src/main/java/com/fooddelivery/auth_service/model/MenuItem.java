package com.fooddelivery.auth_service.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "menu_items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    private String category; // например: "Пицца", "Суши", "Напитки"

    @Column(columnDefinition = "boolean default true")
    private boolean available = true;
}