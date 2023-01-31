package com.ecommerce.tiagocustodio.controller;

import com.ecommerce.tiagocustodio.model.UserModel;
import com.ecommerce.tiagocustodio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserModel> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public UserModel save(@RequestBody UserModel userModel) {
        return userService.save(userModel);
    }

    @GetMapping("/{id}")
    public Optional<UserModel> findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }
}