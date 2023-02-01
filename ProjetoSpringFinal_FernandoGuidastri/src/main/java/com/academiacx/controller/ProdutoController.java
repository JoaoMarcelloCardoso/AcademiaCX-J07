package com.academiacx.controller;

import com.academiacx.handler.exceptions.ParametroInvalidoException;
import com.academiacx.models.ProdutoModel;
import com.academiacx.models.dto.ProdutoDto;
import com.academiacx.models.dto.managed.ManagedProdutoDto;
import com.academiacx.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService produtosService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(produtosService.findAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        if(id == null){
            throw new ParametroInvalidoException("Id não pode ser nulo");
        }

        ProdutoDto produtos = produtosService.findById(id);

        if(produtos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtos);

    }
    @PostMapping("/save")
    public ResponseEntity<ProdutoDto> save(@Valid @RequestBody ManagedProdutoDto managedProdutoDto){
        return ResponseEntity.ok(produtosService.save(managedProdutoDto));
    }

    @PutMapping("/update")
    public ResponseEntity<ProdutoDto> update(@Valid @RequestBody ProdutoDto produtosDto){
        return ResponseEntity.ok(produtosService.update(produtosDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ProdutoDto> delete(@Valid @RequestBody ProdutoDto produtosDto){
        return ResponseEntity.ok(produtosService.delete(produtosDto));
    }
}
