package com.academiacx.controller;

import com.academiacx.model.ViaCepModel;
import com.academiacx.service.ViaCepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cep")
public class ViaCepController {

    private final ViaCepService viaCepService;

    public ViaCepController(ViaCepService viaCepService) {
        this.viaCepService = viaCepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<?> getViaCep(@PathVariable String cep) {

        ViaCepModel response = viaCepService.getViaCep(cep);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }
}
