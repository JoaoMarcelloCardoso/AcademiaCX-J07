package br.com.commerce.academiacx.controller;

import java.util.List;

import br.com.commerce.academiacx.service.ProdutoService;
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

import br.com.commerce.academiacx.model.Produto;
import br.com.commerce.academiacx.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping("/all")
	public ResponseEntity<List<Produto>> findAllByProdutos() {
		
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> findByIdProduto(@PathVariable long id) {
		
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> findAllByNomeProdutos(@PathVariable String nome) {
		
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping("/add")
	public ResponseEntity<Produto> postProduto(@RequestBody Produto produto) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Produto> putProduto(@RequestBody Produto produto) {
		
		return ResponseEntity.ok(repository.save(produto));
	}

	@PutMapping("/produto_pedido/produtos/{idProduto}/pedidos/{idPedido}")
	public ResponseEntity<Produto> putProduto(@PathVariable long idProduto, @PathVariable long idPedido/*, @PathVariable int qtdProduto*/) {
		
		return ResponseEntity.ok(service.compraProduto(idProduto, idPedido/*, qtdProduto*/));
	}
	
	@PutMapping("/produto_lista/produtos/{idProduto}/listaDesejos/{idListaDeDesejo}")
	public ResponseEntity<Produto> adicionaProdutoListaDeDesejos(@PathVariable long idProduto, @PathVariable long idListaDeDesejo) {
		
		return ResponseEntity.ok(service.adicionarProdutoListaDeDesejo(idProduto, idListaDeDesejo));
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduto(@PathVariable long id) {
		
		repository.deleteById(id);
	}

}
