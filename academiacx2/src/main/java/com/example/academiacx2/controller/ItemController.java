package com.example.academiacx2.controller;

import com.example.academiacx2.model.ItemModel;
import com.example.academiacx2.model.dto.ItemDto;
import com.example.academiacx2.service.ItemService;
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
    public ResponseEntity<?> findAll(){
        List<ItemDto> response =  itemService.findall();
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ItemDto findById(@PathVariable Long id){

        return itemService.findById(id);
    }

    @PostMapping("/save")
    public ItemDto insert(@RequestBody ItemDto itemDto) {

        return itemService.insert(itemDto);
    }

    @PutMapping("/update")
    public ItemModel update(@RequestBody ItemModel itemModel) {

        return itemService.update(itemModel);
    }

    @DeleteMapping("/delete")
    public boolean deleteById(@RequestParam(value = "id", required = false) Long id) {

        return itemService.delete(id);
    }

    @GetMapping("/filter")
    public List<ItemModel> filter(@RequestParam(value = "quantidade", required = false) Integer quantidade,
                                  @RequestParam(value = "total", required = false) Double total) {

        return itemService.findByQuantidadeOrTotal(quantidade, total);
    }

    @GetMapping("/id_carrinho")
    public List<ItemModel> filter(@RequestParam(value = "id", required = false) Long id){

        return itemService.findByCarrinhoModel_Id(id);
    }

    @GetMapping("/Cliente{id_carrinho}")
    public List<ItemModel> findBy(@PathVariable Long id_carrinho) {

        return itemService.findByCarrinhoModel_Id(id_carrinho);
    }
}
