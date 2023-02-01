package com.demo.academiacx.controller;

import com.demo.academiacx.model.CarrinhoModel;
import com.demo.academiacx.model.ItemModel;
import com.demo.academiacx.model.dto.carrinho.CarrinhoDto;
import com.demo.academiacx.service.CarrinhoService;
import com.demo.academiacx.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/carrinho")
@AllArgsConstructor
public class CarrinhoController {

    private final CarrinhoService carrinhoService;


    private ProdutoService produtoService;


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

    @PostMapping("/salvar")
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
    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> findByClienteId(@PathVariable Long id){
        CarrinhoDto response = carrinhoService.findByCliente(id);

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }
    @GetMapping("/filter")
    public ResponseEntity<?> findByClienteIdOrIdOrItemId(@RequestParam(value = "cliente_id", required = false) Long cliente_id,
                                                                 @RequestParam(value = "item_id", required = false) Long item_id,
                                                                 @RequestParam(value = "id", required = false) Long id) {

        CarrinhoDto response = carrinhoService.findByClienteIdOrIdOrItemId(id, cliente_id, item_id);

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

}
