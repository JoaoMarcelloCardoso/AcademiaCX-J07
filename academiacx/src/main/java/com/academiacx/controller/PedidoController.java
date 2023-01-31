package com.academiacx.controller;

import com.academiacx.model.PedidoModel;
import com.academiacx.model.dto.PedidoDto;
import com.academiacx.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }


    @GetMapping
    public ResponseEntity<List<PedidoDto>> findAll() {

        List<PedidoDto> response = pedidoService.findAll();

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> findById(@PathVariable Long id) {

        PedidoDto response = pedidoService.findById(id);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }


    @GetMapping("/cliente")
    public ResponseEntity<List<PedidoDto>> findByClienteId(@RequestParam(value = "id") Long id) {

        List<PedidoDto> response = pedidoService.findByClienteId(id);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }


    @PostMapping("/save")
    public ResponseEntity<PedidoDto> insert(@RequestBody PedidoModel pedidoModel,
                                            @RequestParam(value = "cupom", required = false) String cupom) {

        PedidoDto response = pedidoService.insert(pedidoModel, cupom);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }


    @PostMapping("/save/falha")
    public ResponseEntity<PedidoDto> insertFalha(@RequestBody PedidoModel pedidoModel) {

        PedidoDto response = pedidoService.insertFalha(pedidoModel);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

}
