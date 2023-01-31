package com.ecommerce.tiagocustodio.controller;

import com.ecommerce.tiagocustodio.model.ClienteModel;
import com.ecommerce.tiagocustodio.model.ClienteModel;
import com.ecommerce.tiagocustodio.model.UserModel;
import com.ecommerce.tiagocustodio.service.ClienteService;
import com.ecommerce.tiagocustodio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteModel> findAll() {
        return clienteService.findAll();
    }

    @PostMapping
    public ClienteModel save(@RequestBody ClienteModel clienteModel) {
        return clienteService.save(clienteModel);
    }

    @GetMapping("/{id}")
    public Optional<ClienteModel> findById(@PathVariable Long id) {
        return clienteService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        clienteService.deleteById(id);
    }
}