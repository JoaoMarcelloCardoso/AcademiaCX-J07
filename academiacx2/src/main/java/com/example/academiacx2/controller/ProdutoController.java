package com.example.academiacx2.controller;

import com.example.academiacx2.model.ProdutoModel;
import com.example.academiacx2.model.dto.ProdutoDto;
import com.example.academiacx2.service.ProdutoService;
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
    public ResponseEntity<?> findAll(){
        List<ProdutoDto> response =  produtoService.findall();
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ProdutoDto findById(@PathVariable Long id){

        return produtoService.findById(id);
    }

    @PostMapping("/save")
    public ProdutoDto insert(@RequestBody ProdutoDto  produtoDto ) {

        return produtoService.insert(produtoDto);
    }

    @PutMapping("/update")
    public ProdutoModel update(@RequestBody ProdutoModel produtoModel) {

        return produtoService.update(produtoModel);
    }

    @DeleteMapping("/delete")
    public boolean deleteById(@RequestParam(value = "id", required = false) Long id) {

        return produtoService.delete(id);
    }
    @GetMapping("/filter")
    public List<ProdutoModel> filter(@RequestParam(value = "sku", required = false) String sku,
                                     @RequestParam(value = "nome", required = false) String nome) {

        return produtoService.findBySkuOrNome(sku, nome);
    }
}
