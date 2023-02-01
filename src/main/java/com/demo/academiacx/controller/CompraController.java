package com.demo.academiacx.controller;



import com.demo.academiacx.model.CompraModel;
import com.demo.academiacx.model.dto.checkout.CheckoutItemDto;
import com.demo.academiacx.model.dto.compra.CompraDto;
import com.demo.academiacx.service.CompraService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/compra")
@AllArgsConstructor
public class CompraController {
    private final CompraService compraService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<CompraDto> response = compraService.findAll();
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/order-history")
    public ResponseEntity<?> findCompraByClienteId(@RequestParam Long id){

        List<CompraModel> response = compraService.findComprasByClienteId(id);

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/payment/{id}")
    public CheckoutItemDto findById(@PathVariable Long id){

        return compraService.findById(id);
    }

    @PostMapping("/buy")
    public CompraModel Buy(@RequestBody CheckoutItemDto checkoutItemDto){
        return compraService.comprar(checkoutItemDto);
    }

    @PutMapping("/update")
    public CompraModel update(@RequestBody CompraModel compraModel){

        return compraService.update(compraModel);
    }

    @GetMapping("/filter")
    public List<CompraModel> filter(@RequestParam(value = "id_carrinho", required = false) Long id_carrinho,
                                   @RequestParam(value = "id_cliente", required = false) Long id_cliente,
                                   @RequestParam(value = "id_endereco", required = false) Long id_endereco) {

        return compraService.findByCarrinhoModelOrClienteModelOrEnderecoModel(id_carrinho, id_cliente, id_endereco);
    }
}
