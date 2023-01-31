package com.demo.academiacx.controller;

import com.demo.academiacx.model.dto.carrinho.CarrinhoDto;
import com.demo.academiacx.service.CarrinhoService;
import com.demo.academiacx.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/carrinho")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;


    private ProdutoService produtoService;


    public CarrinhoController(CarrinhoService carrinhoService, ProdutoService productService) {
        this.carrinhoService = carrinhoService;
        this.produtoService = productService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {

        List<CarrinhoDto> response = carrinhoService.findAll();

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        CarrinhoDto response = carrinhoService.findById(id);

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<?> insert(@RequestBody CarrinhoDto carrinhoDto) {

        CarrinhoDto response = carrinhoService.insert(carrinhoDto);

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }


    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody CarrinhoDto carrinhoDto) {

        CarrinhoDto response = carrinhoService.update(carrinhoDto);

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public boolean deleteById(@RequestParam(value = "id") Long id) {

        return carrinhoService.delete(id);
    }
    @GetMapping("cliente/{id}")
    public ResponseEntity<?> findByClienteId(@PathVariable Long id){
        CarrinhoDto response = carrinhoService.findByCliente(id);

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

}
