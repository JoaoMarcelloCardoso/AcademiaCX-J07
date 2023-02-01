package com.academiacx.service;

import com.academiacx.handler.exceptions.ForeignKeyException;
import com.academiacx.handler.exceptions.UsuarioNaoEncontradoException;
import com.academiacx.models.ProdutoModel;
import com.academiacx.models.dto.ProdutoDto;
import com.academiacx.models.dto.managed.ManagedProdutoDto;
import com.academiacx.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
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

    public List<ProdutoDto> findAll(){
        return produtoRepository
                .findAll()
                .stream()
                .map(ProdutoDto::new)
                .toList();

    }

    public ProdutoDto findById(Long id) {
        return produtoRepository
                .findById(id)
                .map(ProdutoDto::new)
                .orElse(null);
    }

    public ProdutoDto save(ManagedProdutoDto managedProdutoDto) {
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setNome((managedProdutoDto.getNome()));
        produtoModel.setSku((managedProdutoDto.getSku()));
        produtoRepository.save(produtoModel);
        return new ProdutoDto(produtoModel);
    }

    public ProdutoDto update(ProdutoDto produtoDto){
        Optional<ProdutoModel> produto = produtoRepository.findById(produtoDto.getId());
        if (produto.isPresent()){
            ProdutoModel produtoModel = produto.get();
            produtoModel.setNome((produtoDto.getNome()));
            produtoModel.setSku((produtoDto.getSku()));
            produtoRepository.save(produtoModel);
            return new ProdutoDto(produtoModel);
        }
        throw new UsuarioNaoEncontradoException("Usuário não encontrado");
    }

    public ProdutoDto delete(ProdutoDto produtoDto){
        try {
            Optional<ProdutoModel> produto = produtoRepository.findById(produtoDto.getId());
            if (produto.isPresent()) {
                ProdutoModel produtoModel = produto.get();
                produtoRepository.delete(produtoModel);
                return modelMapper.map(produtoModel, ProdutoDto.class);
            }
        } catch (Exception e) {
            throw new ForeignKeyException("Entidade presente em varias tabelas");

        }
        return null;

    }
}
