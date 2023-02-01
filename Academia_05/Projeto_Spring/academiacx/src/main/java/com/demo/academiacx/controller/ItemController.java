package com.demo.academiacx.controller;

import com.demo.academiacx.model.ItemModel;
import com.demo.academiacx.model.dto.compra.ItemDto;
import com.demo.academiacx.model.dto.produto.ProdutoDto;
import com.demo.academiacx.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/item")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<?> findAll() {

        List<ItemModel> response = itemService.findAll();

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        ItemModel response = itemService.findById(id);

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }
    @GetMapping("/carrinho/{id}")
    public ResponseEntity<?> findByCarrinhoId(@PathVariable Long id) {

        ItemModel response = itemService.findByCarrinhoId(id);

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }
    @PostMapping("/salvar")
    public ResponseEntity<?> insert(@RequestBody ItemDto itemDto) {

        ItemModel response = itemService.insert(itemDto);

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ItemDto itemDto) {

        ItemModel response = itemService.update(itemDto);

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public boolean deleteById(@RequestParam(value = "id") Long id) {

        return itemService.delete(id);
    }
    @DeleteMapping("/delete/{id}")
    public boolean deleteByCarrinhoId(@PathVariable Long id) {

        return itemService.deleteByCarrinho(id);
    }
    @GetMapping("/filter")
    public ResponseEntity<?> findByProdutoIdOrCarrinhoIdOrItemId(@RequestParam(value = "produto_id", required = false) Long produto_id,
                                                          @RequestParam(value = "carrinho_id", required = false) Long carrinho_id,
                                                          @RequestParam(value = "id", required = false) Long id) {

        ItemModel response = itemService.findByProdutoIdOrCarrinhoIdOrItemId(id, produto_id, carrinho_id);

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }
}
