package com.demo.academiacx.controller;

import com.demo.academiacx.model.PedidoModel;
import com.demo.academiacx.model.dto.PedidoDto;
import com.demo.academiacx.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<PedidoDto> response = pedidoService.findAll();
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        PedidoDto response = pedidoService.findById(id);
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody PedidoDto pedidoDto) {
        PedidoDto response = pedidoService.save(pedidoDto);
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody PedidoDto pedidoDto) {
        PedidoDto response = pedidoService.save(pedidoDto);
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public boolean deleteById(@RequestParam(value = "id") Long id){
        pedidoService.deleteById(id);
        return true;
    }

//    @GetMapping("/history")
//    public ResponseEntity<?> findHistory(@RequestParam(value = "clienteId") Long clienteId,
//                                            @RequestParam(value = "enderecoId") Long enderecoId,
//                                            @RequestParam(value = "itemId") Long itemId){
//
//        List<PedidoDto> response = pedidoService.findHistory(clienteId, enderecoId, itemId);
//
//        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
//    }
}


