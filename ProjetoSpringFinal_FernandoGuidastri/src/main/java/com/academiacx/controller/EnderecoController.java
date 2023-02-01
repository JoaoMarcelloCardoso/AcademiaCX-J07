package com.academiacx.controller;

import com.academiacx.handler.exceptions.ParametroInvalidoException;
import com.academiacx.models.EnderecoModel;
import com.academiacx.models.dto.EnderecoDto;
import com.academiacx.service.EnderecoService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;
    @GetMapping("/findAll")
    public ResponseEntity<List<EnderecoDto>> findAll(){
        return ResponseEntity.ok(enderecoService.findAll());
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        if(id == null){
            throw new ParametroInvalidoException("Id não pode ser nulo");
        }

        EnderecoDto enderecos = enderecoService.findById(id);

        if(enderecos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(enderecos);

    }
    @GetMapping("/findEnderecoByClienteId/{id}")
    public ResponseEntity<List<EnderecoDto>> findEnderecoByClienteId(@PathVariable Long id){
        return ResponseEntity.ok(enderecoService.findEnderecoByClienteId(id));
    }

    @PostMapping("/save/{clienteid}")
    public ResponseEntity<EnderecoDto> save(
            @Valid @RequestBody EnderecoDto enderecoDto,
            @PathVariable Long clienteid
    ){
        return ResponseEntity.ok(enderecoService.save(enderecoDto,clienteid));
    }

    @PutMapping("/update")
    public ResponseEntity<EnderecoDto> update(@Valid @RequestBody EnderecoDto enderecoDto){
        return ResponseEntity.ok(enderecoService.update(enderecoDto));

    }

    @DeleteMapping("/delete")
    public ResponseEntity<EnderecoDto> delete(@Valid @RequestBody EnderecoDto enderecoDto){
        return ResponseEntity.ok(enderecoService.delete(enderecoDto));
    }





}
