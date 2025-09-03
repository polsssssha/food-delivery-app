package com.fooddelivery.auth_service.repository;

import com.fooddelivery.auth_service.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByCategory(String category);
    List<MenuItem> findByAvailableTrue();
    List<MenuItem> findByNameContainingIgnoreCase(String name);
}