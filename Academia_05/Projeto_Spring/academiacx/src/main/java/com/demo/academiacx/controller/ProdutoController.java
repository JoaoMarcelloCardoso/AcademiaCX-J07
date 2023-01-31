package com.demo.academiacx.controller;

import com.demo.academiacx.model.dto.produto.ProdutoDto;
import com.demo.academiacx.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {

        List<ProdutoDto> response = produtoService.findAll();

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        ProdutoDto response = produtoService.findById(id);

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<?> insert(@RequestBody ProdutoDto produtoDto) {

        ProdutoDto response = produtoService.insert(produtoDto);

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ProdutoDto produtoDto) {

        ProdutoDto response = produtoService.update(produtoDto);

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public boolean deleteById(@RequestParam(value = "id") Long id) {

        return produtoService.delete(id);
    }

}
