package com.demo.academiacx.controller;

import com.demo.academiacx.model.dto.endereco.EnderecoDto;
import com.demo.academiacx.service.EnderecoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/endereco")
@AllArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<?> findAll() {

        List<EnderecoDto> response = enderecoService.findAll();

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        EnderecoDto response = enderecoService.findById(id);

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }
    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> findByClienteId(@PathVariable Long id) {

        List<EnderecoDto> response = enderecoService.findByClienteId(id);

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }
    @PostMapping("/salvar")
    public ResponseEntity<?> insert(@RequestBody EnderecoDto enderecoDto) {

        EnderecoDto response = enderecoService.insert(enderecoDto);

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody EnderecoDto enderecoDto) {

        EnderecoDto response = enderecoService.update(enderecoDto);

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public boolean deleteById(@RequestParam(value = "id") Long id) {

        return enderecoService.delete(id);
    }

}
