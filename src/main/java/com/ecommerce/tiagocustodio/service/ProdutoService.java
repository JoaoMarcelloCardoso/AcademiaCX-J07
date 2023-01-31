package com.ecommerce.tiagocustodio.service;

import com.ecommerce.tiagocustodio.handler.exceptions.ParametroInvalidoException;
import com.ecommerce.tiagocustodio.handler.exceptions.RecursoNaoEncontradoException;
import com.ecommerce.tiagocustodio.model.ProdutoModel;
import com.ecommerce.tiagocustodio.model.UserModel;
import com.ecommerce.tiagocustodio.repository.ProdutoRepository;
import com.ecommerce.tiagocustodio.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoModel> findAll() {
        return produtoRepository.findAll();
    }

    public ProdutoModel save(ProdutoModel produtoModel) {
        return produtoRepository.save(produtoModel);
    }

    public Optional<ProdutoModel> findById(Long id) {
        return produtoRepository.findById(id);
    }

    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }
}