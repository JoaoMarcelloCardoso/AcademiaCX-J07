package com.example.academiacx2.controller;

import com.example.academiacx2.model.ClienteModel;
import com.example.academiacx2.model.dto.ClienteDto;
import com.example.academiacx2.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<ClienteDto> response =  clienteService.findall();
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ClienteDto findById(@PathVariable Long id){

        return clienteService.findById(id);
    }

    @PostMapping("/save")
    public ClienteDto insert(@RequestBody ClienteDto clienteDto) {

        return clienteService.insert(clienteDto);
    }

    @PutMapping("/update")
    public ClienteModel update(@RequestBody ClienteModel clienteModel) {

        return clienteService.update(clienteModel);
    }

    @DeleteMapping("/delete")
    public boolean deleteById(@RequestParam(value = "id", required = false) Long id) {

        return clienteService.delete(id);
    }
    @GetMapping("/filter")
    public List<ClienteModel> filter(@RequestParam(value = "cpf", required = false) String cpf,
                                     @RequestParam(value = "nome", required = false) String nome) {

        return clienteService.findByCpfOrNome(cpf, nome);
    }
}