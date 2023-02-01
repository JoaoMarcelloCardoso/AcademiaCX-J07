package com.demo.academiacx.controller;

import com.demo.academiacx.model.EnderecoModel;
import com.demo.academiacx.model.dto.CarrinhoDto;
import com.demo.academiacx.model.dto.EnderecoDto;
import com.demo.academiacx.repository.EnderecoRepository;
import com.demo.academiacx.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/address")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {

        List<EnderecoDto> response = enderecoService.findAll();
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        EnderecoDto response = enderecoService.findById(id);
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);

    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody EnderecoDto enderecoDto){

        EnderecoDto response = enderecoService.save(enderecoDto);
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);

    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody EnderecoDto enderecoDto){

        EnderecoDto response = enderecoService.update(enderecoDto);
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);

    }

    @DeleteMapping("/delete")
    public boolean deleteById(@RequestParam(value = "id") Long id){
        enderecoService.delete(id);
        return true;
    }

    @GetMapping("/cliente")
    public ResponseEntity<?> findByClienteId(@RequestParam(value = "id") Long id) {

        List<EnderecoDto> response = enderecoService.findByClienteId(id);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();

    }

}
