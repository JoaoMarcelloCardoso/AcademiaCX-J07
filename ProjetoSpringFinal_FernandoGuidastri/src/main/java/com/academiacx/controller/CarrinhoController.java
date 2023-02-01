package com.academiacx.controller;

import com.academiacx.models.CarrinhoModel;
import com.academiacx.models.dto.CarrinhoDto;
import com.academiacx.service.CarrinhoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @GetMapping("/findAll")
    public ResponseEntity<List<CarrinhoDto>> findAll(){
        return ResponseEntity.ok(carrinhoService.findAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<CarrinhoDto> findById(@PathVariable Long id){
        CarrinhoDto carrinhos = carrinhoService.findById(id);

        if(carrinhos == null){
            return ResponseEntity
                    .notFound()
                    .build();
        }
        return ResponseEntity.ok(carrinhoService.findById(id));
    }

    @GetMapping("/findCarrinhoByClienteId/{id}")
    public ResponseEntity<List<CarrinhoDto>> findCarrinhoByClienteId(@PathVariable Long id){
        return ResponseEntity.ok(carrinhoService.findCarrinhoByClienteId(id));
    }

    @PostMapping("/save/{clientId}")
    public ResponseEntity<CarrinhoDto> save(
            @RequestBody CarrinhoModel carrinhoModel,
            @PathVariable Long clientId
    ){
        return ResponseEntity.ok(carrinhoService.save(carrinhoModel,clientId));
    }

    @PutMapping("/update")
    public ResponseEntity<CarrinhoDto> update(@Valid @RequestBody CarrinhoDto carrinhoDto){
        return ResponseEntity.ok(carrinhoService.update(carrinhoDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CarrinhoDto> delete(@Valid @RequestBody CarrinhoDto carrinhoDto){
        return ResponseEntity.ok(carrinhoService.delete(carrinhoDto));
    }

}












