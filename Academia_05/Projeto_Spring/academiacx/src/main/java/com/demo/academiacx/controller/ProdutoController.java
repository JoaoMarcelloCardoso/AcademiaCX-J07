package com.demo.academiacx.controller;

import com.demo.academiacx.handler.exceptions.RecursoNaoEncontradoException;
import com.demo.academiacx.model.dto.produto.ProdutoDto;
import com.demo.academiacx.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/produto")
@AllArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;


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

    @PostMapping("/salvar")
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
    @GetMapping("/filter")
    public ProdutoDto findByNomeOrSkuOrId(@RequestParam(value = "nome", required = false) String nome,
                                          @RequestParam(value = "sku", required = false)String sku,
                                          @RequestParam(value = "id", required = false) Long id) {

        return produtoService.findByNomeOrSkuOrId(id, nome, sku);
    }
}
