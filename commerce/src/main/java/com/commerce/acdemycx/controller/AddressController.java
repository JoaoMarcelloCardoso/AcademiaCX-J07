package com.commerce.acdemycx.controller;

import com.commerce.acdemycx.model.AddressModel;
import com.commerce.acdemycx.model.dto.request.AddressDtoRequest;
import com.commerce.acdemycx.model.dto.response.AddressDtoResponse;
import com.commerce.acdemycx.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {

        List<AddressDtoResponse> response = addressService.findAll();

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        AddressDtoResponse response = addressService.findById(id);

        return response == null? ResponseEntity.notFound().build() : ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<?> insert(@RequestBody AddressDtoRequest addressDtoRequest) {

        AddressDtoResponse response = addressService.insert(addressDtoRequest);

        return response == null? ResponseEntity.badRequest().build(): ResponseEntity.ok(response) ;
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody AddressDtoRequest addressDtoRequest) {

        AddressDtoResponse response = addressService.update(addressDtoRequest);

        return response == null? ResponseEntity.badRequest().build() : ResponseEntity.ok(response);
    }



    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteById(@RequestParam(value = "id", required = false) Long id) {

        boolean success = addressService.delete(id);

        return success? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
