package com.commerce.acdemycx.controller;


import com.commerce.acdemycx.model.dto.request.ClientDtoRequest;
import com.commerce.acdemycx.model.dto.response.ClientDtoResponse;
import com.commerce.acdemycx.model.ClientModel;
import com.commerce.acdemycx.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping
    public ResponseEntity<?> findAll() {

        List<ClientDtoResponse> response = clientService.findAll();

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {

        ClientDtoResponse response = clientService.findById(id);

        return response == null? ResponseEntity.notFound().build() : ResponseEntity.ok(response);

    }

    @PostMapping("/save")
    public ResponseEntity<?> insert(@RequestBody ClientDtoRequest clientDtoRequest) {

        ClientDtoResponse response = clientService.insert(clientDtoRequest);

        return response == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(response);
    }


    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ClientModel clientModel) {

        ClientDtoResponse response = clientService.update(clientModel);



        return response == null? ResponseEntity.badRequest().build() : ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteById(@RequestParam(value = "id", required = false) long id) {

        boolean success = clientService.delete(id);

        return success? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }


}
