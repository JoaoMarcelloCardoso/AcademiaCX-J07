package com.ecommerce.tiagocustodio.controller;

import com.ecommerce.tiagocustodio.model.PrecoModel;
import com.ecommerce.tiagocustodio.model.dto.PrecoDto;
import com.ecommerce.tiagocustodio.service.PrecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/preco")
public class PrecoController {
    @Autowired
    private PrecoService precoService;

    /*@GetMapping
    public ResponseEntity<?> findAll() {

        List<PrecoModel> response = precoService.findAll();

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }*/

    @GetMapping("/{id}")
    public PrecoDto findById(@PathVariable Long id) {

        return precoService.findById(id);
    }

    /*@PostMapping("/save")
    public PrecoModel insert(@RequestBody PrecoModel precoModel) {

        return precoService.insert(precoModel);
    }*/

    @PutMapping("/update")
    public PrecoModel update(@RequestBody PrecoModel precoModel) {

        return precoService.update(precoModel);
    }

    @DeleteMapping("/delete")
    public boolean deleteById(@RequestParam(value = "id", required = false) Long id) {

        return precoService.delete(id);
    }

}