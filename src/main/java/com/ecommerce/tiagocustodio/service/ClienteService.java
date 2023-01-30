package com.ecommerce.tiagocustodio.service;

import com.ecommerce.tiagocustodio.handler.exceptions.RecursoNaoEncontradoException;
import com.ecommerce.tiagocustodio.model.ClienteModel;
import com.ecommerce.tiagocustodio.model.dto.ClienteDto;
import com.ecommerce.tiagocustodio.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final ModelMapper modelMapper;

    public ClienteService(ClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    public ClienteDto findById(Long id) {
        ClienteModel clienteModel = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com id: " + id));
        return new ClienteDto(clienteModel);
    }

    /*public ClienteDto insert(ClienteDto clienteDto) {
        clienteDto.setId(null);
        ClienteModel clienteModel = new ClienteModel(clienteDto);
        clienteModel = clienteRepository.save(clienteModel);
        return new ClienteDto(clienteModel);
    }*/

    public ClienteDto update(ClienteModel clienteDto) {
        ClienteModel clienteModel = clienteRepository.findById(clienteDto.getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com id: " + clienteDto.getId()));
        clienteModel.setCpf(clienteDto.getCpf());
        clienteModel.setNome(clienteDto.getNome());
        clienteModel = clienteRepository.save(clienteModel);
        return new ClienteDto(clienteModel);
    }

    public boolean delete(Long id) {
        findById(id);
        clienteRepository.deleteById(id);
        return true;
    }

    public List<ClienteDto> findAll() {
        List<ClienteModel> clienteModels = clienteRepository.findAll();
        return clienteModels.stream().map(ClienteDto::new).collect(Collectors.toList());
    }
}