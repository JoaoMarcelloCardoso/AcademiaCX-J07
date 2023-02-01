package com.academiacx.service;

import com.academiacx.handler.exceptions.ForeignKeyException;
import com.academiacx.handler.exceptions.UsuarioNaoEncontradoException;
import com.academiacx.models.ClienteModel;
import com.academiacx.models.dto.ClienteDto;
import com.academiacx.models.dto.managed.ManagedClienteDto;
import com.academiacx.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public ClienteService(
            ClienteRepository clienteRepository,
            PasswordEncoder passwordEncoder,
            ModelMapper modelMapper
    ) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    public List<ClienteDto> findAll(){
        return clienteRepository
                .findAll()
                .stream()
                .map(ClienteDto::new)
                .toList();
    }

    public ClienteDto findById(Long id) {
        Optional<ClienteModel> cliente = clienteRepository.findById(id);
        return cliente
                .map(ClienteDto::new)
                .orElse(null);
    }

    public ClienteDto save(ManagedClienteDto clienteManaged) {
        Optional<ClienteModel> userExists = clienteRepository.findByCpf(clienteManaged.getCpf());
        if(userExists.isPresent()){
            String mensagem = "Usuário já cadastrado";
            return new ClienteDto(mensagem,mensagem,mensagem);
        }
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setPassword(passwordEncoder.encode(clienteManaged.getPassword()));
        clienteModel.setCpf(clienteManaged.getCpf());
        clienteModel.setNome(clienteManaged.getNome());
        clienteModel.setEmail(clienteManaged.getEmail());
        clienteRepository.save(clienteModel);
        return new ClienteDto(clienteModel);
    }

    public ClienteDto update(ManagedClienteDto clienteManaged){
        Optional<ClienteModel> cliente = clienteRepository.findByCpf(clienteManaged.getCpf());
        if (cliente.isPresent()){
            ClienteModel clienteModel = cliente.get();
            clienteModel.setPassword(passwordEncoder.encode(clienteManaged.getPassword()));
            clienteModel.setCpf(clienteManaged.getCpf());
            clienteModel.setNome(clienteManaged.getNome());
            clienteRepository.save(clienteModel);
            return new ClienteDto(clienteModel);
        }
        throw new UsuarioNaoEncontradoException("Usuário não encontrado");
    }

    public ClienteDto delete(ClienteDto clienteDto){
        try {
            Optional<ClienteModel> cliente = clienteRepository.findByCpf(clienteDto.getCpf());
            if (cliente.isPresent()) {
                ClienteModel clienteModel = cliente.get();
                clienteRepository.delete(clienteModel);
                return new ClienteDto(clienteModel);
            }
        } catch (Exception e) {
            throw new ForeignKeyException("Entidade presente em varias tabelas");

        }
        return null;

    }
}

