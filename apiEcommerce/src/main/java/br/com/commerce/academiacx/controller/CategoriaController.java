package br.com.commerce.academiacx.controller;

import java.util.List;

import br.com.commerce.academiacx.model.Categoria;
import br.com.commerce.academiacx.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

    @Autowired
    private CategoriaRepository repository;

    @GetMapping("/all")
    public ResponseEntity<List<Categoria>> findAllCategorias() {

        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findByIdCategoria(@PathVariable long id) {

        return repository.findById(id)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Categoria> postCategoria(@RequestBody Categoria categoria) {

        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Categoria> putCategoria(@RequestBody Categoria categoria) {

        return ResponseEntity.ok(repository.save(categoria));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategoria(@PathVariable long id) {

        repository.deleteById(id);
    }

}
