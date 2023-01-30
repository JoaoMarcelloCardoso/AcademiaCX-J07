package com.ecommerce.tiagocustodio.controller;

import com.ecommerce.tiagocustodio.model.CarrinhoModel;
import com.ecommerce.tiagocustodio.model.dto.CarrinhoDto;
import com.ecommerce.tiagocustodio.model.dto.ClienteDto;
import com.ecommerce.tiagocustodio.model.dto.UserDto;
import com.ecommerce.tiagocustodio.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/carrinho")
public class CarrinhoController {
    @Autowired
    private CarrinhoService carrinhoService;

    @GetMapping
    public ResponseEntity<List<CarrinhoDto>> findAll() {

        List<CarrinhoDto> response = carrinhoService.findAll();

        return response == null || response.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public CarrinhoDto findById(@PathVariable Long id) {
        return carrinhoService.findById(id);
    }

    @PostMapping("/save")
    public CarrinhoDto insert(@RequestBody CarrinhoDto carrinhoDto) {

        return carrinhoService.insert(carrinhoDto);
    }

    /*@PutMapping("/update")
    public ResponseEntity<CarrinhoDto> update(@RequestBody CarrinhoModel carrinhoModel) {
        CarrinhoDto updatedCarrinho = carrinhoService.update(carrinhoModel);
        return updatedCarrinho != null ?
               ResponseEntity.ok(updatedCarrinho) :
               ResponseEntity.unprocessableEntity().build();
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if(carrinhoService.delete(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}



