package com.fooddelivery.auth_service.controller;

import com.fooddelivery.auth_service.model.User;
import com.fooddelivery.auth_service.repository.UserRepository;
import com.fooddelivery.auth_service.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private PasswordService passwordService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test")
    public String test() {
        return "Auth Service is working! 🚀 Time: " + LocalDateTime.now();
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        // Хэшируем пароль перед сохранением!
        user.setPassword(passwordService.hashPassword(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully: " + user.getEmail());
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}