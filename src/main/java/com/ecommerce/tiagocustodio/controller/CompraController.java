package com.ecommerce.tiagocustodio.controller;

import com.ecommerce.tiagocustodio.model.CompraModel;
import com.ecommerce.tiagocustodio.model.UserModel;
import com.ecommerce.tiagocustodio.service.CompraService;
import com.ecommerce.tiagocustodio.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/compra")
public class CompraController {
    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping
    public List<CompraModel> findAll() {
        return compraService.findAll();
    }

    @PostMapping
    public CompraModel save(@RequestBody CompraModel compraModel) {
        return compraService.save(compraModel);
    }

    @GetMapping("/{id}")
    public Optional<CompraModel> findById(@PathVariable Long id) {
        return compraService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        compraService.deleteById(id);
    }
}