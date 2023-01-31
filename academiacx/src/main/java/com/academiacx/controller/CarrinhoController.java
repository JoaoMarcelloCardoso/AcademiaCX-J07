package com.academiacx.controller;

import com.academiacx.model.CarrinhoModel;
import com.academiacx.model.dto.CarrinhoDto;
import com.academiacx.service.CarrinhoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/carrinho")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @GetMapping
    public ResponseEntity<List<CarrinhoDto>> findAll() {

        List<CarrinhoDto> response = carrinhoService.findAll();

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<CarrinhoDto> findById(@PathVariable Long id) {

        CarrinhoDto response = carrinhoService.findById(id);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }


    @GetMapping("/cliente")
    public ResponseEntity<List<CarrinhoDto>> findByClienteId(@RequestParam(value = "id") Long id) {

        List<CarrinhoDto> response = carrinhoService.findByClienteId(id);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }


    @PostMapping("/save")
    public ResponseEntity<CarrinhoDto> insert(@RequestBody CarrinhoModel carrinhoModel) {

        CarrinhoDto response = carrinhoService.insert(carrinhoModel);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }


    @PutMapping("/update")
    public ResponseEntity<CarrinhoDto> update(@RequestBody CarrinhoModel carrinhoModel) {

        CarrinhoDto response = carrinhoService.update(carrinhoModel);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestParam(value = "id") Long id) {

        Boolean success = carrinhoService.delete(id);

        return success ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }


    @DeleteMapping("/delete/cliente")
    public ResponseEntity<Boolean> deleteByClienteId(@RequestParam(value = "id") Long id) {

        Boolean success = carrinhoService.deleteByClienteId(id);

        return success ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }

}
