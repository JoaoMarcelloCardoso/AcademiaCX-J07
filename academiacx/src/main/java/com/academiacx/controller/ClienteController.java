package com.academiacx.controller;

import com.academiacx.model.ClienteModel;
import com.academiacx.model.dto.ClienteDto;
import com.academiacx.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @GetMapping
    public ResponseEntity<List<ClienteDto>> findAll() {

        List<ClienteDto> response = clienteService.findAll();

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> findById(@PathVariable Long id) {

        ClienteDto response = clienteService.findById(id);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }


    @GetMapping("/filter")
    public ResponseEntity<List<ClienteDto>> filter(@RequestParam(value = "cpf", required = false) String cpf,
                                                   @RequestParam(value = "email", required = false) String email,
                                                   @RequestParam(value = "telefone", required = false) String telefone) {

        List<ClienteDto> response = clienteService.findByCpfOrEmailOrTelefone(cpf, email, telefone);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }


    @PostMapping("/save")
    public ResponseEntity<ClienteDto> insert(@RequestBody ClienteModel clienteModel) {

        ClienteDto response = clienteService.insert(clienteModel);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }


    @PutMapping("/update")
    public ResponseEntity<ClienteDto> update(@RequestBody ClienteModel clienteModel) {

        ClienteDto response = clienteService.update(clienteModel);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestParam(value = "id") Long id) {

        Boolean success = clienteService.delete(id);

        return success ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }

}
