package com.academiacx.service;

import com.academiacx.handler.exceptions.ForeignKeyException;
import com.academiacx.handler.exceptions.UsuarioNaoEncontradoException;
import com.academiacx.models.ClienteModel;
import com.academiacx.models.EnderecoModel;
import com.academiacx.models.dto.EnderecoDto;
import com.academiacx.repository.ClienteRepository;
import com.academiacx.repository.EnderecoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    public EnderecoService(
            EnderecoRepository enderecoRepository,
            ModelMapper modelMapper,
            ClienteRepository clienteRepository
    ) {
        this.enderecoRepository = enderecoRepository;
        this.modelMapper = modelMapper;
        this.clienteRepository = clienteRepository;
    }

    public List<EnderecoDto> findAll() {
        return enderecoRepository
                .findAll()
                .stream()
                .map(EnderecoDto::new)
                .toList();
    }

    public EnderecoDto findById(Long id) {
        Optional<EnderecoModel> endereco = enderecoRepository.findById(id);
        return endereco
                .map(EnderecoDto::new)
                .orElse(null);
    }

    public List<EnderecoDto> findEnderecoByClienteId(Long id) {
        List<EnderecoModel> enderecos = enderecoRepository.findEnderecoModelsByClienteId(id).get();
        return enderecos
                .stream()
                .map(EnderecoDto::new)
                .toList();
    }

    public EnderecoDto save(EnderecoDto enderecoDto, Long clienteid) {
        ClienteModel cliente = clienteRepository.findById(clienteid).get();
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setLogradouro((enderecoDto.getLogradouro()));
        enderecoModel.setNumero((enderecoDto.getNumero()));
        enderecoModel.setCidade((enderecoDto.getCidade()));
        enderecoModel.setBairro((enderecoDto.getBairro()));
        enderecoModel.setUf((enderecoDto.getUf()));
        enderecoModel.setCliente(cliente);
        enderecoRepository.save(enderecoModel);
        return new EnderecoDto(enderecoModel);
    }

    public EnderecoDto update(EnderecoDto enderecoDto) {
        Optional<EnderecoModel> endereco = enderecoRepository.findById(enderecoDto.getId());
        if (endereco.isPresent()) {
            EnderecoModel enderecoModel = endereco.get();
            enderecoModel.setLogradouro((enderecoDto.getLogradouro()));
            enderecoModel.setNumero((enderecoDto.getNumero()));
            enderecoModel.setCidade((enderecoDto.getCidade()));
            enderecoModel.setBairro((enderecoDto.getBairro()));
            enderecoModel.setUf((enderecoDto.getUf()));
            enderecoRepository.save(enderecoModel);
            return new EnderecoDto(enderecoModel);
        } else {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado");
        }

    }

    public EnderecoDto delete(EnderecoDto enderecoDto) {
        try {
            Optional<EnderecoModel> endereco = enderecoRepository.findById(enderecoDto.getId());
            if (endereco.isPresent()) {
                EnderecoModel enderecoModel = endereco.get();
                enderecoRepository.delete(enderecoModel);
                return new EnderecoDto(enderecoModel);
            }
        } catch (Exception e) {
            throw new ForeignKeyException("Entidade presente em varias tabelas");
        }
        return null;

    }
}