package com.commerce.acdemycx.controller;

import com.commerce.acdemycx.model.CartModel;
import com.commerce.acdemycx.model.PurchaseModel;
import com.commerce.acdemycx.service.CartService;
import com.commerce.acdemycx.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cart")
public class CartController {


    @Autowired
    private CartService cartService;

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping
    public ResponseEntity<?> findAll() {

        List<CartModel> response = cartService.findAll();

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {

        CartModel response = cartService.findById(id);

        return response == null? ResponseEntity.notFound().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/purchases")
    public ResponseEntity<?> findAllPurchases() {

        List<PurchaseModel> response = purchaseService.findAll();

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/purchases/{id}")
    public ResponseEntity<?> findPurchaseById(@PathVariable long id) {

        PurchaseModel response = purchaseService.findById(id);

        return response == null? ResponseEntity.notFound().build() : ResponseEntity.ok(response);
    }

    @PostMapping("/new")
    public ResponseEntity<?> insert(@RequestBody CartModel cartModel, @RequestParam(value = "client_id", required = true) long client_id) {

        CartModel response = cartService.insert(cartModel, client_id);

        return response == null? ResponseEntity.badRequest().build(): ResponseEntity.ok(response) ;
    }

    @PostMapping("finalizar-compra")
    public ResponseEntity<?> buy(@RequestParam(value = "cart_id", required = true) long cart_id,
                                    @RequestParam(value = "address_id", required = true) long addres_id,
                                 @RequestParam(value = "payment_method", required = true) String paymentMethod) {


        PurchaseModel response = purchaseService.buy(cart_id, addres_id, paymentMethod) ;

        return response == null? ResponseEntity.badRequest().build(): ResponseEntity.ok(response) ;
    }


    // removendo o update do carrinho, pois vejo que a forma de atualizar o carrinho é por meio da inserção ou remoção de items
    // ou deletando o carrinho completamente
//    @PutMapping("/update")
//    public ResponseEntity<?> update(@RequestBody CartModel cartModel) {
//
//        CartModel response = cartService.update(cartModel);
//
//        return response == null? ResponseEntity.badRequest().build() : ResponseEntity.ok(response);
//    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteById(@RequestParam(value = "id", required = false) long id) {

        boolean success = cartService.delete(id);

        return success? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
