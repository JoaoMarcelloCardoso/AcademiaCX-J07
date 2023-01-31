package com.academiacx.controller;


import com.academiacx.model.CadastroModel;
import com.academiacx.model.dto.CadastroDto;
import com.academiacx.service.CadastroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cadastro")
public class CadastroController {

    private final CadastroService cadastroService;

    public CadastroController(CadastroService cadastroService) {
        this.cadastroService = cadastroService;
    }


    @PostMapping
    public ResponseEntity<CadastroDto> cadastrar(@RequestBody CadastroModel cadastroModel) {

        CadastroDto response = cadastroService.cadastrar(cadastroModel);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }
}
