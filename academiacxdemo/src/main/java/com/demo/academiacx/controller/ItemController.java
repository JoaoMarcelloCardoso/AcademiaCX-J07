package com.demo.academiacx.controller;

import com.demo.academiacx.model.ClienteModel;
import com.demo.academiacx.model.ItemModel;
import com.demo.academiacx.model.dto.ItemDto;
import com.demo.academiacx.repository.ItemRepository;
import com.demo.academiacx.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
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

        List<ItemDto> response = itemService.findAll();
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        ItemDto response = itemService.findById(id);
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);

    }

    @PostMapping("/save")
    public ResponseEntity<?> insert(@RequestBody ItemDto itemDto){

        ItemDto response = itemService.save(itemDto);
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ItemDto itemDto){

        ItemDto response = itemService.update(itemDto);
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public boolean deleteById(@RequestParam(value = "id") Long id){

        itemService.delete(id);
        return true;
    }

    @GetMapping("/carrinho")
    public ResponseEntity<List<ItemDto>> findByCarrinhoId(@RequestParam(value = "id") Long id){

        List<ItemDto> response = itemService.findByCarrinhoId(id);

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);

    }

    @DeleteMapping("/carrinho")
    public boolean deleteByCarrinhoId(@RequestParam(value = "id") Long id){

        itemService.deleteByCarrinhoId(id);
        return true;
    }

}
