package com.example.academiacx2.controller;

import com.example.academiacx2.model.VendaModel;
import com.example.academiacx2.model.dto.VendaDto;
import com.example.academiacx2.service.VendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/venda")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) { this.vendaService = vendaService; }

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<VendaDto> response = vendaService.findAll();
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/{id}_payment")
    public VendaDto findById(@PathVariable Long id){

        return vendaService.findById(id);
    }

    @PostMapping("/save_payment")
    public VendaDto insert(@RequestBody VendaDto vendaDto){

        return vendaService.insert(vendaDto);
    }

    @PutMapping("/update_payment")
    public VendaModel update(@RequestBody VendaModel vendaModel){

        return vendaService.update(vendaModel);
    }

    @DeleteMapping("/delete_payment")
    public boolean deleteById(@RequestParam(value = "id", required = false) Long id){

        return vendaService.delete(id);
    }

    @GetMapping("/filter_payment")
    public List<VendaModel> filter(@RequestParam(value = "id_carrinho", required = false) Long id_carrinho,
                                   @RequestParam(value = "id_cliente", required = false) Long id_cliente,
                                   @RequestParam(value = "id_endereco", required = false) Long id_endereco,
                                   @RequestParam(value = "id_item", required = false) Long id_item) {

        return vendaService.findByCarrinhoModelOrClienteModelOrEnderecoModelOrItemModel(id_carrinho, id_cliente, id_endereco, id_item);
    }
}
