package com.example.academiacx2.controller;

import com.example.academiacx2.model.EnderecoModel;
import com.example.academiacx2.model.dto.EnderecoDto;
import com.example.academiacx2.service.EnderecoService;
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
    public ResponseEntity<?> findAll(){
        List<EnderecoDto> response =  enderecoService.findall();
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public EnderecoDto findById(@PathVariable Long id){

        return enderecoService.findById(id);
    }

    @PostMapping("/save")
    public EnderecoDto insert(@RequestBody EnderecoDto enderecoDto) {

        return enderecoService.insert(enderecoDto);
    }

    @PutMapping("/update")
    public EnderecoModel update(@RequestBody EnderecoModel enderecoModel) {

        return enderecoService.update(enderecoModel);
    }
    @DeleteMapping("/delete")
    public boolean deleteById(@RequestParam(value = "id", required = false) Long id) {

        return enderecoService.delete(id);
    }

    @GetMapping("/filter")
    public List<EnderecoModel> filter(@RequestParam(value = "cep", required = false) String cep,
                                      @RequestParam(value = "logradouro", required = false) String logradouro,
                                      @RequestParam(value = "numero", required = false) String numero,
                                      @RequestParam(value = "bairro", required = false) String bairro,
                                      @RequestParam(value = "cidade", required = false) String cidade,
                                      @RequestParam(value = "uf", required = false) String uf) {

        return enderecoService.findByCepOrLogradouroOrNumeroOrBairroOrCidadeOrUf(cep, logradouro, numero, bairro, cidade, uf);
    }

    @GetMapping("/id_cliente")
    public List<EnderecoModel> filter(@RequestParam(value = "id", required = false) Long id){

        return enderecoService.findByClienteModel_Id(id);
    }

    @GetMapping("/Cliente{id_cliente}")
    public List<EnderecoModel> findBy(@PathVariable Long id_cliente) {

        return enderecoService.findByClienteModel_Id(id_cliente);
    }
}
