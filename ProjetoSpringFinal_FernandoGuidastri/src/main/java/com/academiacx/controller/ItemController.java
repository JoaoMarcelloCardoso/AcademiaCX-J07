package com.academiacx.controller;

import com.academiacx.models.ItemModel;
import com.academiacx.models.ProdutoModel;
import com.academiacx.models.dto.ItemDto;
import com.academiacx.repository.ProdutoRepository;
import com.academiacx.service.ItemService;
import com.academiacx.handler.exceptions.ParametroInvalidoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    private ProdutoRepository produtoRepository;

    public ItemController(ProdutoRepository produtoRepository){
        this.produtoRepository=produtoRepository;
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(itemService.findAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ItemDto> findById(@PathVariable Long id) {

        if (id == null) {
            throw new ParametroInvalidoException("Id não pode ser nulo");
        }

        ItemDto items = itemService.findById(id);

        if (items == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(items);

    }
    @GetMapping("/findItemByCarrinhoId/{id}")
    public ResponseEntity<List<ItemDto>> findItemByCarrinhoId(@PathVariable Long id){
        return ResponseEntity.ok(itemService.findItemByCarrinhoId(id));
    }

    @PostMapping("/save")
    public ResponseEntity<ItemDto> save(@Valid @RequestBody ItemModel itemModel) {
        itemModel.setTotal(itemModel.getQuantidade()*itemModel.getPreco().getValor());

        return ResponseEntity.ok(itemService.save(itemModel));
    }

    @PutMapping("/update")
    public ResponseEntity<ItemDto> update(@Valid @RequestBody ItemDto itemDto) {
        return ResponseEntity.ok(itemService.update(itemDto));

    }

    @DeleteMapping("/deleteByCarrinhoId/{id}")
    public ResponseEntity<List<ItemDto>> deleteByCarrinhoId(@PathVariable Long id){
        return ResponseEntity.ok(itemService.deleteByCarrinhoId(id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ItemDto> delete(@Valid @RequestBody ItemDto itemDto) {
        return ResponseEntity.ok(itemService.delete(itemDto));
    }
}


