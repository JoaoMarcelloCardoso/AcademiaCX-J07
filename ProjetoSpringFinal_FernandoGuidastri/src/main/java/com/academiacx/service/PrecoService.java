package com.academiacx.service;

import com.academiacx.handler.exceptions.ForeignKeyException;
import com.academiacx.handler.exceptions.UsuarioNaoEncontradoException;
import com.academiacx.models.PrecoModel;
import com.academiacx.models.dto.PrecoDto;
import com.academiacx.repository.PrecoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrecoService {
    private final PrecoRepository precoRepository;


    private final ModelMapper modelMapper;

    public PrecoService(PrecoRepository precoRepository, ModelMapper modelMapper) {
        this.precoRepository = precoRepository;
        this.modelMapper = modelMapper;
    }

    public List<PrecoDto> findAll(){
        return precoRepository
                .findAll()
                .stream()
                .map(PrecoDto::new)
                .toList();
    }

    public PrecoDto findById(Long id) {
        return precoRepository
                .findById(id)
                .map(PrecoDto::new)
                .orElse(null);
    }

    public List<PrecoDto> findPrecoByProdutoId(Long id) {
        return precoRepository
                .findPrecoModelsByProdutoId(id)
                .map(precoModels -> precoModels
                        .stream()
                        .map(PrecoDto::new)
                        .toList()).orElse(null);
    }

    public PrecoDto save(PrecoDto precoDto) {
        PrecoModel precoModel = new PrecoModel();
        precoModel.setValor((precoDto.getValor()));
        precoRepository.save(precoModel);
        return new PrecoDto(precoModel);
    }

    public PrecoDto update(PrecoDto precoDto){
        Optional<PrecoModel> preco = precoRepository.findById(precoDto.getId());
        if (preco.isPresent()){
            PrecoModel precoModel = preco.get();
            precoModel.setValor(precoDto.getValor());
            precoRepository.save(precoModel);
            return new PrecoDto(precoModel);
        }
        throw new UsuarioNaoEncontradoException("Usuário não encontrado");
    }

    public PrecoDto delete(PrecoDto precoDto){
        try {
            Optional<PrecoModel> preco = precoRepository.findById(precoDto.getId());
            if (preco.isPresent()) {
                PrecoModel precoModel = preco.get();
                precoRepository.delete(precoModel);
                return modelMapper.map(precoModel, PrecoDto.class);
            }
        } catch (Exception e) {
            throw new ForeignKeyException("Entidade presente em varias tabelas");

        }
        return null;

    }
}
