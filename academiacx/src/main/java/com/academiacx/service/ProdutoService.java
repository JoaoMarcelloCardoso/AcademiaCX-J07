package com.academiacx.service;

import com.academiacx.handler.exceptions.ConstraintViolationException;
import com.academiacx.handler.exceptions.ParametroInvalidoException;
import com.academiacx.handler.exceptions.RecursoNaoEncontradoException;
import com.academiacx.model.ProdutoModel;
import com.academiacx.model.dto.ProdutoDto;
import com.academiacx.repository.ProdutoRepository;
import com.academiacx.utils.ValidacaoUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final ModelMapper modelMapper;

    public ProdutoService(ProdutoRepository produtoRepository, ModelMapper modelMapper) {
        this.produtoRepository = produtoRepository;
        this.modelMapper = modelMapper;
    }


    public List<ProdutoDto> findAll() {

        List<ProdutoModel> produtoModels = produtoRepository.findAll();

        return modelMapper.map(produtoModels, new TypeToken<List<ProdutoDto>>() {
        }.getType());
    }


    public ProdutoDto findById(Long id) {

        ValidacaoUtils.validarId(id, "Id de produto informado é inválido!");

        Optional<ProdutoModel> produtoModel = produtoRepository.findById(id);

        if (!produtoModel.isPresent() || produtoModel.isEmpty()) {
            throw new RecursoNaoEncontradoException("Id informado não encontrado!");
        }

        return modelMapper.map(produtoModel.get(), ProdutoDto.class);
    }


    public List<ProdutoDto> findByNomeOrSku(String nome, String sku) {

        Optional<List<ProdutoModel>> produtoModels = produtoRepository.findByNomeOrSku(nome, sku);

        if (!produtoModels.isPresent() || produtoModels.get().isEmpty()) {
            throw new RecursoNaoEncontradoException("Produto não encontrado com os filtros informados!");
        }

        return modelMapper.map(produtoModels.get(), new TypeToken<List<ProdutoDto>>() {
        }.getType());
    }


    public ProdutoDto insert(ProdutoModel produtoModel) {

        produtoModel.setId(null);

        validarProduto(produtoModel);

        try {
            return new ProdutoDto(produtoRepository.save(produtoModel));
        } catch (Exception e) {
            throw new ConstraintViolationException("Algum dado inserido viola uma restrição do banco de dados (Dado duplicado)");
        }

    }


    public ProdutoDto update(ProdutoModel produtoModel) {

        if (produtoModel == null) {
            throw new ParametroInvalidoException("O Produto Model deve ser informado!");
        }

        ValidacaoUtils.validarId(produtoModel.getId(), "Id de produto informado é inválido!");

        Optional<ProdutoModel> produto = produtoRepository.findById(produtoModel.getId());

        if (!produto.isPresent() || produto.isEmpty()) {
            throw new RecursoNaoEncontradoException("Id informado não encontrado!");
        }

        validarProduto(produtoModel);

        try {
            return new ProdutoDto(produtoRepository.save(produtoModel));
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


    private void validarProduto(ProdutoModel produtoModel) {
        if (produtoModel.getNome() == null) {
            throw new ParametroInvalidoException("O nome do produto deve ser informado!");
        }

        if (produtoModel.getSku() == null) {
            throw new ParametroInvalidoException("O SKU do produto deve ser informado!");
        }

        if (produtoModel.getPreco() == null) {
            throw new ParametroInvalidoException("O preço do produto deve ser informado!");
        }
    }
}
