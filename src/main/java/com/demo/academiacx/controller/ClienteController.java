package com.demo.academiacx.controller;

import com.demo.academiacx.model.UserModel;
import com.demo.academiacx.model.dto.user.ClienteDto;
import com.demo.academiacx.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
@AllArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;


    @GetMapping
    public ResponseEntity<?> findAll() {

        List<ClienteDto> response = clienteService.findAll();

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        ClienteDto response = clienteService.findById(id);

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> insert(@RequestBody ClienteDto clienteDto) {

        ClienteDto response = clienteService.insert(clienteDto);

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ClienteDto clienteDto) {

        ClienteDto response = clienteService.update(clienteDto);

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public boolean deleteById(@RequestParam(value = "id") Long id) {

        return clienteService.delete(id);
    }

    @GetMapping("/filter")
    public ClienteDto filter(@RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "id", required = false) Long id) {

        return clienteService.findByNameOrId(name, id);
    }
}
