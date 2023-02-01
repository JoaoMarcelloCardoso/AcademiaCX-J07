package com.demo.academiacx.controller;

import com.demo.academiacx.model.PrecoModel;
import com.demo.academiacx.model.dto.EnderecoDto;
import com.demo.academiacx.model.dto.PrecoDto;
import com.demo.academiacx.service.PrecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/price")
public class PrecoController {

    private final PrecoService precoService;

    public PrecoController(PrecoService precoService) {
        this.precoService = precoService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {

        List<PrecoDto> response = precoService.findAll();
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        PrecoDto response = precoService.findById(id);
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<?> insert(@RequestBody PrecoDto precoDto){

        PrecoDto response = precoService.save(precoDto);
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody PrecoDto precoDto){

        PrecoDto response = precoService.update(precoDto);
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public boolean deleteById(@RequestParam(value = "id") Long id){

        precoService.delete(id);
        return true;
    }

    @GetMapping("/produto")
    public ResponseEntity<List<PrecoDto>> findByProdutoId(@RequestParam(value = "id") Long id) {

        List<PrecoDto> response = precoService.findByProdutoId(id);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

}
