package com.ecommerce.tiagocustodio.controller;

import com.ecommerce.tiagocustodio.model.ProdutoModel;
import com.ecommerce.tiagocustodio.model.ProdutoModel;
import com.ecommerce.tiagocustodio.model.UserModel;
import com.ecommerce.tiagocustodio.service.ProdutoService;
import com.ecommerce.tiagocustodio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<ProdutoModel> findAll() {
        return produtoService.findAll();
    }

    @PostMapping
    public ProdutoModel save(@RequestBody ProdutoModel produtoModel) {
        return produtoService.save(produtoModel);
    }

    @GetMapping("/{id}")
    public Optional<ProdutoModel> findById(@PathVariable Long id) {
        return produtoService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        produtoService.deleteById(id);
    }
}