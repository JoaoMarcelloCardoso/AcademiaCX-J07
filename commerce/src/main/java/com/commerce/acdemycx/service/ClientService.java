package com.commerce.acdemycx.service;

import com.commerce.acdemycx.model.dto.request.ClientDtoRequest;
import com.commerce.acdemycx.model.dto.response.ClientDtoResponse;
import com.commerce.acdemycx.handler.exceptions.*;
import com.commerce.acdemycx.model.ClientModel;
import com.commerce.acdemycx.repository.ClientRepository;
import com.commerce.acdemycx.utils.ValidationUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientService {
    private final ClientRepository clientRepository;

    private final ModelMapper modelMapper;

    public ClientService(ClientRepository clientRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;

    }

    public List<ClientDtoResponse> findAll() {
        try {
            List<ClientModel> clientList = clientRepository.findAll();
            if (clientList.isEmpty()) {
                throw new SemConteudoException("Não há conteúdo no endpoint client");
            }
            return modelMapper.map(clientList, new TypeToken<List<ClientDtoResponse>>() {
            }.getType());
        } catch (Exception e) {
            throw new ErroDeFormatoException("Erro ao buscar clientes");
        }
    }

    public ClientDtoResponse findById(Long id) {
        if (id == null) {
            throw new ParametroInvalidoException("Id informado inválido");
        }

        try {
            ClientModel clientModel = clientRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoExeception("Cliente não encontrado com o id informado"));
            return modelMapper.map(clientModel, ClientDtoResponse.class);
        } catch (Exception e) {
            throw new ErroDeFormatoException("Erro ao buscar cliente pelo id");
        }
    }

    public ClientDtoResponse findById(ClientModel clientModel) {
        if (clientModel == null) {
            throw new ParametroInvalidoException("A Client Model deve ser informada");
        }

        if (clientModel.getId() == null) {
            throw new ParametroInvalidoException("A Client Model deve conter um id");
        }

        try {
            ClientModel client = clientRepository.findById(clientModel.getId()).orElseThrow(() -> new RecursoNaoEncontradoExeception("Cliente não encontrado com o id informado"));
            return modelMapper.map(client, ClientDtoResponse.class);
        } catch (Exception e) {
            throw new ErroDeFormatoException("Erro ao buscar cliente pelo id");
        }
    }


    public ClientDtoResponse insert(ClientDtoRequest clientDtoRequest) {
        if (clientRepository.findByUsername(clientDtoRequest.getUsername()).isPresent()){
            throw new UsuarioExistente("O usuario " + clientDtoRequest.getUsername() + "já existe");
        }
        validateClient(clientDtoRequest);
        ClientModel clientModel = modelMapper.map(clientDtoRequest, ClientModel.class);
        clientRepository.save(clientModel);
        return modelMapper.map(clientModel, ClientDtoResponse.class);
    }


    public ClientDtoResponse update(ClientModel clientModel) {
        if (!clientRepository.existsById(clientModel.getId())) {
            throw new RecursoNaoEncontradoExeception("Não existe cliente com o id informado");
        }

        validateUpdate(clientModel);
        ClientDtoRequest client = modelMapper.map(clientModel, ClientDtoRequest.class);
        clientModel.setPassword(client.getPassword());
        clientRepository.save(clientModel);

        return modelMapper.map(clientModel, ClientDtoResponse.class);
    }
    public boolean delete(Long id) {
        if (id == null || !clientRepository.existsById(id)) {
            throw new RecursoNaoEncontradoExeception("Não existe cliente com o id informado");
        }
        try {
            clientRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new ErroDeFormatoException("Erro ao deletar cliente");
        }


    }

    private void validateClient(ClientDtoRequest clientDtoRequest) {

        ValidationUtils.validateNotNullOrEmpty(clientDtoRequest.getCpf(), "CPF");
        ValidationUtils.validateCPF(clientDtoRequest.getCpf());

        ValidationUtils.validateNotNullOrEmpty(clientDtoRequest.getNome(), "Nome");
        ValidationUtils.validateName(clientDtoRequest.getNome());


        ValidationUtils.validateNotNullOrEmpty(clientDtoRequest.getPasswordDecode(), "Password");
        ValidationUtils.validatePassword(clientDtoRequest.getPasswordDecode());


    }

    private void validateUpdate(ClientModel clientModel){
        if (!clientRepository.existsById(clientModel.getId())) {
            throw new RecursoNaoEncontradoExeception("Não existe cliente com o id informado");
        }
        ClientModel existClientModel = clientRepository.findById(clientModel.getId()).orElse(null);
        if (existClientModel != null) {
            if (clientModel.getNome() == null || clientModel.getNome().isEmpty()) {
                clientModel.setNome(existClientModel.getNome());
            }
            if (clientModel.getUsername() == null || clientModel.getUsername().isEmpty()) {
                clientModel.setUsername(existClientModel.getUsername());
            }
            if (clientModel.getPassword() == null || clientModel.getPassword().isEmpty()) {
                clientModel.setPassword(existClientModel.getPassword());
            }
            if (clientModel.getCpf() == null || clientModel.getCpf().isEmpty()) {
                clientModel.setCpf(existClientModel.getCpf());
            }
            if (clientModel.getAddresses() == null || clientModel.getAddresses().isEmpty()) {
                clientModel.setAddresses(existClientModel.getAddresses());
            }
            if (clientModel.getCarts() == null || clientModel.getCarts().isEmpty()) {
                clientModel.setCarts(existClientModel.getCarts());
            }

            validateClient(modelMapper.map(clientModel, ClientDtoRequest.class));

            }
//
    }


}
