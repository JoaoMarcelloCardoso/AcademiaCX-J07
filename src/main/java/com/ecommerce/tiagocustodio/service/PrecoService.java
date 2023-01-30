package com.ecommerce.tiagocustodio.service;

import com.ecommerce.tiagocustodio.handler.exceptions.ParametroInvalidoException;
import com.ecommerce.tiagocustodio.handler.exceptions.RecursoNaoEncontradoException;
import com.ecommerce.tiagocustodio.model.PrecoModel;
import com.ecommerce.tiagocustodio.model.dto.PrecoDto;
import com.ecommerce.tiagocustodio.repository.PrecoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PrecoService {
    private final PrecoRepository precoRepository;
    private final ModelMapper modelMapper;

    public PrecoService(PrecoRepository precoRepository, ModelMapper modelMapper) {
        this.precoRepository = precoRepository;
        this.modelMapper = modelMapper;
    }

    public PrecoDto findById(PrecoModel precoModel) {
        if (precoModel == null) {
            throw new ParametroInvalidoException("A Preco Model deve informada");
        }

        if (precoModel.getId() == null) {
            throw new ParametroInvalidoException("A Preco Model deve conter um id");
        }

        try {
            precoModel = precoRepository.findById(precoModel.getId()).get();
        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Id informado não encontrado");
        }

        return modelMapper.map(precoModel, PrecoDto.class);
    }

    public PrecoDto findById(Long id) {
        if (id == null) {
            throw new ParametroInvalidoException("Id informado inválido");
        }

        PrecoModel precoModel = new PrecoModel();
        try {
            precoModel = precoRepository.findById(id).get();
        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Id informado não encontrado");
        }

        return modelMapper.map(precoModel, PrecoDto.class);
    }

    /*public PrecoDto insert(PrecoDto precoDto) {
        precoDto.setId(null);

        PrecoDto result = new PrecoDto(precoRepository.save(new PrecoModel(precoDto)));

        return result;
    }*/

    public PrecoModel update(PrecoModel precoModel) {
        findById(precoModel);

        return precoRepository.save(precoModel);
    }

    public boolean delete(Long id) {
        findById(id);

        precoRepository.deleteById(id);

        return true;
    }

}