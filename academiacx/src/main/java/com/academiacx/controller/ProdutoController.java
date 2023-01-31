package com.academiacx.controller;

import com.academiacx.model.ProdutoModel;
import com.academiacx.model.dto.ProdutoDto;
import com.academiacx.service.ProdutoService;
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
    public ResponseEntity<List<ProdutoDto>> findAll() {

        List<ProdutoDto> response = produtoService.findAll();

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> findById(@PathVariable Long id) {

        ProdutoDto response = produtoService.findById(id);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }


    @GetMapping("/filter")
    public ResponseEntity<List<ProdutoDto>> filter(@RequestParam(value = "nome", required = false) String nome,
                                                   @RequestParam(value = "sku", required = false) String sku) {

        List<ProdutoDto> response = produtoService.findByNomeOrSku(nome, sku);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }


    @PostMapping("/save")
    public ResponseEntity<ProdutoDto> insert(@RequestBody ProdutoModel produtoModel) {

        ProdutoDto response = produtoService.insert(produtoModel);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }


    @PutMapping("/update")
    public ResponseEntity<ProdutoDto> update(@RequestBody ProdutoModel produtoModel) {

        ProdutoDto response = produtoService.update(produtoModel);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestParam(value = "id") Long id) {

        Boolean success = produtoService.delete(id);

        return success ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }

}
