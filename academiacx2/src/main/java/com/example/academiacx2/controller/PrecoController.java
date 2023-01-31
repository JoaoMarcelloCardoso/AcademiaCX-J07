package com.example.academiacx2.controller;

import com.example.academiacx2.model.PrecoModel;
import com.example.academiacx2.model.dto.PrecoDto;
import com.example.academiacx2.service.PrecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/preco")
public class PrecoController {
    private final PrecoService precoService;

    public PrecoController(PrecoService precoService) {
        this.precoService = precoService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<PrecoDto> response =  precoService.findall();
        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public PrecoDto findById(@PathVariable Long id){

        return precoService.findById(id);
    }

    @PostMapping("/save")
    public PrecoDto insert(@RequestBody PrecoDto precoDto) {

        return precoService.insert(precoDto);
    }

    @PutMapping("/update")
    public PrecoModel update(@RequestBody PrecoModel precoModel) {

        return precoService.update(precoModel);
    }
    @DeleteMapping("/delete")
    public boolean deleteById(@RequestParam(value = "id", required = false) Long id) {

        return precoService.delete(id);
    }

    @GetMapping("/filter")
    public List<PrecoModel> filter(@RequestParam(value = "valor", required = false) Double valor,
                                   @RequestParam(value = "id", required = false) Long id) {

        return precoService.findByValorOrId(valor, id);
    }

    @GetMapping("/id_produto")
    public List<PrecoModel> filter(@RequestParam(value = "id", required = false) Long id){

        return precoService.findByProdutoModel_Id(id);
    }

    @GetMapping("/Produto{id_produto}")
    public List<PrecoModel> findBy(@PathVariable Long id_produto) {

        return precoService.findByProdutoModel_Id(id_produto);
    }
}
