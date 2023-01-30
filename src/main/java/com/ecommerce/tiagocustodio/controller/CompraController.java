package com.ecommerce.tiagocustodio.controller;

import com.ecommerce.tiagocustodio.model.dto.CompraDto;
import com.ecommerce.tiagocustodio.model.dto.UserDto;
import com.ecommerce.tiagocustodio.service.CompraService;
import com.ecommerce.tiagocustodio.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/compra")
public class CompraController {
    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {

        List<CompraDto> response = compraService.findAll();

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public CompraDto findById(@PathVariable Long id) {

        return compraService.findById(id);
    }
    @PostMapping("/save")
    public CompraDto insert(@RequestBody CompraDto compraDto) {

        return compraService.insert(compraDto);
    }

}
