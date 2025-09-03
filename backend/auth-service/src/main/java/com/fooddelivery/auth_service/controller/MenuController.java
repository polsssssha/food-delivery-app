package com.fooddelivery.auth_service.controller;

import com.fooddelivery.auth_service.model.MenuItem;
import com.fooddelivery.auth_service.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuItemRepository menuItemRepository;

    // Получить все блюда
    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    // Получить блюда по категории
    @GetMapping("/category/{category}")
    public List<MenuItem> getMenuItemsByCategory(@PathVariable String category) {
        return menuItemRepository.findByCategory(category);
    }

    // Получить только доступные блюда
    @GetMapping("/available")
    public List<MenuItem> getAvailableMenuItems() {
        return menuItemRepository.findByAvailableTrue();
    }

    // Поиск блюд по названию
    @GetMapping("/search")
    public List<MenuItem> searchMenuItems(@RequestParam String name) {
        return menuItemRepository.findByNameContainingIgnoreCase(name);
    }

    // Получить блюдо по ID
    @GetMapping("/{id}")
    public MenuItem getMenuItem(@PathVariable Long id) {
        return menuItemRepository.findById(id).orElse(null);
    }
}