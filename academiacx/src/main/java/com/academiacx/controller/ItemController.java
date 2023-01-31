package com.academiacx.controller;

import com.academiacx.model.ItemModel;
import com.academiacx.model.dto.ItemDto;
import com.academiacx.service.ItemService;
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
    public ResponseEntity<List<ItemDto>> findAll() {

        List<ItemDto> response = itemService.findAll();

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> findById(@PathVariable Long id) {

        ItemDto response = itemService.findById(id);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }


    @GetMapping("/carrinho")
    public ResponseEntity<List<ItemDto>> findByCarrinhoId(@RequestParam(value = "id") Long id) {

        List<ItemDto> response = itemService.findByCarrinhoId(id);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }


    @PostMapping("/save")
    public ResponseEntity<ItemDto> insert(@RequestBody ItemModel itemModel) {

        ItemDto response = itemService.insert(itemModel);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }


    @PutMapping("/update")
    public ResponseEntity<ItemDto> update(@RequestBody ItemModel itemModel) {

        ItemDto response = itemService.update(itemModel);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestParam(value = "id") Long id) {

        Boolean success = itemService.delete(id);

        return success ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }


    @DeleteMapping("/delete/carrinho")
    public ResponseEntity<Boolean> deleteByCarrinhoId(@RequestParam(value = "id") Long id) {

        Boolean success = itemService.deleteByCarrinhoId(id);

        return success ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }

}
