package com.demo.academiacx.controller;

import com.demo.academiacx.model.dto.CarrinhoDto;
import com.demo.academiacx.service.CarrinhoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/car")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {

        List<CarrinhoDto> response = carrinhoService.findAll();
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        CarrinhoDto response = carrinhoService.findById(id);
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<?> insert(@RequestBody CarrinhoDto carrinhoDto){

        CarrinhoDto response = carrinhoService.save(carrinhoDto);
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody CarrinhoDto carrinhoDto){

        CarrinhoDto response = carrinhoService.update(carrinhoDto);
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public boolean deleteById(@RequestParam(value = "id") Long id){
        carrinhoService.delete(id);
        return true;
    }

    @GetMapping("/cliente")
    public ResponseEntity<List<CarrinhoDto>> findByClienteId(@RequestParam(value = "id") Long id) {

        List<CarrinhoDto> response = carrinhoService.findByClienteId(id);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

}
