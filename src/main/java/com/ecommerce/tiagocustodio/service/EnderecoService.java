package com.ecommerce.tiagocustodio.service;

import com.ecommerce.tiagocustodio.handler.exceptions.RecursoNaoEncontradoException;
import com.ecommerce.tiagocustodio.model.EnderecoModel;
import com.ecommerce.tiagocustodio.model.dto.EnderecoDto;
import com.ecommerce.tiagocustodio.repository.EnderecoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    private final ModelMapper modelMapper;

    public EnderecoService(EnderecoRepository enderecoRepository, ModelMapper modelMapper) {
        this.enderecoRepository = enderecoRepository;
        this.modelMapper = modelMapper;
    }

    public EnderecoDto findById(Long id) {
        EnderecoModel enderecoModel = enderecoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Endereco não encontrado com id: " + id));
        return new EnderecoDto(enderecoModel);
    }

    /*public EnderecoDto insert(EnderecoDto enderecoDto) {
        enderecoDto.setId(null);
        EnderecoModel enderecoModel = new EnderecoModel(enderecoDto);
        enderecoModel = enderecoRepository.save(enderecoModel);
        return new EnderecoDto(enderecoModel);
    }*/

    public EnderecoDto update(EnderecoModel enderecoDto) {
        EnderecoModel enderecoModel = enderecoRepository.findById(enderecoDto.getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Endereco não encontrado com id: " + enderecoDto.getId()));
        enderecoModel.setLogradouro(enderecoDto.getLogradouro());
        enderecoModel.setCidade(enderecoDto.getCidade());
        enderecoModel = enderecoRepository.save(enderecoModel);
        return new EnderecoDto(enderecoModel);
    }

    public boolean delete(Long id) {
        findById(id);
        enderecoRepository.deleteById(id);
        return true;
    }
}