package com.demo.academiacx.service;

import com.demo.academiacx.handler.ConflitoRecursoException;
import com.demo.academiacx.handler.ParametroInvalidoException;
import com.demo.academiacx.handler.RecursoNaoEncontradoException;
import com.demo.academiacx.model.ProdutoModel;
import com.demo.academiacx.model.dto.ProdutoDto;
import com.demo.academiacx.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ModelMapper modelMapper;

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository, ModelMapper modelMapper) {
        this.produtoRepository = produtoRepository;
        this.modelMapper = modelMapper;
    }

    public List<ProdutoDto> findAll() {

        List<ProdutoModel> produtoModel = produtoRepository.findAll();

        return modelMapper.map(produtoModel, new TypeToken<List<ProdutoDto>>(){
        }.getType());
    }

    public ProdutoDto findById(Long id){

        if(id == null){
            throw new ParametroInvalidoException("Id informado inválido");
        }

        ProdutoModel produtoModel = new ProdutoModel();

        try {

            produtoModel = produtoRepository.findById(id).get();

        } catch (Exception e){
            throw new RecursoNaoEncontradoException("Id informado não encontrado");

        }

        return modelMapper.map(produtoModel, ProdutoDto.class);
    }

    public ProdutoDto findById(ProdutoModel produtoModel){

        if(produtoModel == null){
            throw new ParametroInvalidoException("O Produto Model precisa ser informado");
        }

        if(produtoModel.getId() == null){
            throw new ParametroInvalidoException("O Produto Model precisa ter um id");
        }

        try {

            produtoModel = produtoRepository.findById(produtoModel.getId()).get();

        } catch (Exception e){
            throw new RecursoNaoEncontradoException("Id informado não encontrado");

        }

        return modelMapper.map(produtoModel, ProdutoDto.class);    }

    public ProdutoDto save(ProdutoDto produtoDto){

        produtoDto.setId(null);

        ProdutoModel produtoModel = produtoRepository.save(modelMapper.map(produtoDto, ProdutoModel.class));

        return modelMapper.map(produtoModel, ProdutoDto.class);
    }

    public ProdutoDto update(ProdutoDto produtoDto){

        findById(produtoDto.getId());

        ProdutoModel produtoModel = produtoRepository.save(modelMapper.map(produtoDto, ProdutoModel.class));

        return modelMapper.map(produtoModel, ProdutoDto.class);

    }

    public boolean delete(Long id){

        try {
            produtoRepository.deleteById(id);

        } catch (Exception e){

            throw new ConflitoRecursoException("Solicitação atual conflitou com o recurso que está no servidor");
        }

        return true;
    }
}
