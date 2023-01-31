package com.ecommerce.tiagocustodio.controller;

import com.ecommerce.tiagocustodio.model.CarrinhoModel;
import com.ecommerce.tiagocustodio.model.CarrinhoModel;
import com.ecommerce.tiagocustodio.model.ClienteModel;
import com.ecommerce.tiagocustodio.model.UserModel;
import com.ecommerce.tiagocustodio.service.CarrinhoService;
import com.ecommerce.tiagocustodio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {
    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @GetMapping
    public List<CarrinhoModel> findAll() {
        return carrinhoService.findAll();
    }

    @PostMapping
    public CarrinhoModel save(@RequestBody CarrinhoModel carrinhoModel) {
        return carrinhoService.save(carrinhoModel);
    }

    @GetMapping("/{id}")
    public Optional<CarrinhoModel> findById(@PathVariable Long id) {
        return carrinhoService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        carrinhoService.deleteById(id);
    }
}