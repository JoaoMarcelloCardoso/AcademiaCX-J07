package com.ecommerce.tiagocustodio.controller;

import com.ecommerce.tiagocustodio.model.ItemModel;
import com.ecommerce.tiagocustodio.model.dto.ItemDto;
import com.ecommerce.tiagocustodio.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<?> findAll() {

        List<ItemDto> response = itemService.findAll();

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ItemDto findById(@PathVariable Long id) {

        return itemService.findById(id);
    }

    @PostMapping("/save")
    public ItemDto insert(@RequestBody ItemDto itemDto) {

        return itemService.insert(itemDto);
    }

    /*@GetMapping("/{carrinhoId}")
    public List<ItemDto> findByCarrinhoId(@PathVariable Long carrinhoId) {
        return itemService.findByCarrinhoId(carrinhoId);
    }*/

    @PutMapping("/update")
    public ItemDto update(@RequestBody ItemModel itemModel) {

        return itemService.update(itemModel);
    }

    @DeleteMapping("/delete")
    public boolean deleteById(@RequestParam(value = "id", required = false) Long id) {

        return itemService.delete(id);
    }

}