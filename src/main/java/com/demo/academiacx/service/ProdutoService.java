package com.demo.academiacx.service;

import com.demo.academiacx.handler.exceptions.ConstraintViolationException;
import com.demo.academiacx.handler.exceptions.ParametroInvalidoException;
import com.demo.academiacx.handler.exceptions.RecursoNaoEncontradoException;
import com.demo.academiacx.model.ProdutoModel;
import com.demo.academiacx.model.dto.produto.ProdutoDto;
import com.demo.academiacx.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final ModelMapper modelMapper;

    public List<ProdutoDto> findAll() {

        List<ProdutoModel> produtoModels = produtoRepository.findAll();

        return modelMapper.map(produtoModels, new TypeToken<List<ProdutoDto>>() {
        }.getType());
    }

    public ProdutoDto findById(ProdutoModel produtoModel) {

        if (produtoModel == null) {
            throw new ParametroInvalidoException("O Produto Model deve ser informado!");
        }

        if (produtoModel.getId() == null) {
            throw new ParametroInvalidoException("O Produto Model deve conter um id!");
        }

        try {
            produtoModel = produtoRepository.findById(produtoModel.getId()).get();

        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Id informado não encontrado!");
        }

        return modelMapper.map(produtoModel, ProdutoDto.class);
    }

    public ProdutoDto findById(Long id) {

        if (id == null) {
            throw new ParametroInvalidoException("Id informado inválido");
        }

        ProdutoModel produtoModel = new ProdutoModel();
        try {
            produtoModel = produtoRepository.findById(id).get();

        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Id informado não encontrado!");
        }

        return modelMapper.map(produtoModel, ProdutoDto.class);
    }

    public ProdutoDto insert(ProdutoDto produtoDto) {

        produtoDto.setId(null);

        try {
            return new ProdutoDto(produtoRepository.save(new ProdutoModel(produtoDto)));
        } catch (Exception e) {
            throw new ConstraintViolationException("Algum dado inserido viola uma restrição do banco de dados (Dado duplicado)");
        }

    }

    public ProdutoDto update(ProdutoDto produtoDto) {

        findById(new ProdutoModel(produtoDto));

        try {
            return new ProdutoDto(produtoRepository.save(new ProdutoModel(produtoDto)));
        } catch (Exception e) {
            throw new ConstraintViolationException("Algum dado inserido viola uma restrição do banco de dados (Dado duplicado)");
        }

    }

    public boolean delete(Long id) {

        findById(id);

        try {
            produtoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new ConstraintViolationException("Esta ação viola a integridade dos dados presentes no banco de dados!");
        }

    }

    public ProdutoDto findByNomeOrSkuOrId(Long id, String nome, String sku) {
        Optional<ProdutoModel> produtoModel = produtoRepository.findByNomeOrSkuOrId(nome, sku, id);

        if (produtoModel.isPresent()) {
            return modelMapper.map(produtoModel, ProdutoDto.class);
        } else {
            throw new RecursoNaoEncontradoException("Usuário não encontrado");
        }
    }
}
