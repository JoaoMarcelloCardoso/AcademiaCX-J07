package com.academiacx.controller;

import com.academiacx.model.EnderecoModel;
import com.academiacx.model.dto.EnderecoDto;
import com.academiacx.service.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }


    @GetMapping
    public ResponseEntity<List<EnderecoDto>> findAll() {

        List<EnderecoDto> response = enderecoService.findAll();

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDto> findById(@PathVariable Long id) {

        EnderecoDto response = enderecoService.findById(id);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }


    @GetMapping("/cliente")
    public ResponseEntity<List<EnderecoDto>> findByClienteId(@RequestParam(value = "id") Long id) {

        List<EnderecoDto> response = enderecoService.findByClienteId(id);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }


    @GetMapping("/filter")
    public ResponseEntity<List<EnderecoDto>> filter(@RequestParam(value = "bairro", required = false) String bairro,
                                                    @RequestParam(value = "cidade", required = false) String cidade,
                                                    @RequestParam(value = "uf", required = false) String uf) {

        List<EnderecoDto> response = enderecoService.findByBairroOrCidadeOrUf(bairro, cidade, uf);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }


    @PostMapping("/save")
    public ResponseEntity<EnderecoDto> insert(@RequestBody EnderecoModel enderecoModel) {

        EnderecoDto response = enderecoService.insert(enderecoModel);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }


    @PutMapping("/update")
    public ResponseEntity<EnderecoDto> update(@RequestBody EnderecoModel enderecoModel) {

        EnderecoDto response = enderecoService.update(enderecoModel);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestParam(value = "id") Long id) {

        Boolean success = enderecoService.delete(id);

        return success ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }

}
