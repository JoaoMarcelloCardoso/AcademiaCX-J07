package com.demo.academiacx.controller;

import com.demo.academiacx.model.ItemModel;
import com.demo.academiacx.model.dto.compra.ItemDto;
import com.demo.academiacx.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

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
//    @GetMapping("/carrinho/{id}")
//    public ResponseEntity<?> findByCarrinhoId(@PathVariable Long id) {
//
//        ItemDto response = itemService.findByCarrinhoId(id);
//
//        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
//    }

    @PostMapping("/save")
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
//    @DeleteMapping("/delete/{id}")
//    public boolean deleteByCarrinhoId(@PathVariable Long id) {
//
//        return itemService.deleteByCarrinho(id);
//    }
}
