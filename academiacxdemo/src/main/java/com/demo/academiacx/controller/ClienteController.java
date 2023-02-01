package com.demo.academiacx.controller;

import com.demo.academiacx.model.dto.ClienteDto;
import com.demo.academiacx.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")

public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<ClienteDto> response = clienteService.findAll();
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        ClienteDto response = clienteService.findById(id);
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ClienteDto clienteDto){

        ClienteDto response = clienteService.save(clienteDto);
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ClienteDto clienteDto){
        ClienteDto response = clienteService.update(clienteDto);
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public boolean deleteById(@RequestParam(value = "id") Long id){
       return clienteService.delete(id);

    }

    @GetMapping("/filter")
    public ClienteDto filter(@RequestParam(value = "username", required = true) String username){
        return clienteService.findByUsername(username);
    }

    @GetMapping("/find")
    public ClienteDto findCpf(@RequestParam(value = "cpf", required = true) String cpf){

        return clienteService.findByCpf(cpf);
    }

}
