package com.fooddelivery.auth_service.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderItem {
    private Long menuItemId;
    private String name;
    private Integer quantity;
    private BigDecimal price;


    public OrderItem(Long menuItemId, String name, Integer quantity, BigDecimal price) {
        this.menuItemId = menuItemId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }


    public OrderItem() {}
}