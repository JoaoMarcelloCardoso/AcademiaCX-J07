package com.academiacx.service;

import com.academiacx.handler.exceptions.ParametroNullException;
import com.academiacx.model.CadastroModel;
import com.academiacx.model.ClienteModel;
import com.academiacx.model.dto.CadastroDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CadastroService {

    private final ClienteService clienteService;

    private final EnderecoService enderecoService;

    private final ModelMapper modelMapper;

    public CadastroService(ClienteService clienteService, EnderecoService enderecoService, ModelMapper modelMapper) {
        this.clienteService = clienteService;
        this.enderecoService = enderecoService;
        this.modelMapper = modelMapper;
    }


    public CadastroDto cadastrar(CadastroModel cadastroModel) {

        validarCadastro(cadastroModel);

        enderecoService.insert(cadastroModel.getEndereco());

        return modelMapper.map(cadastroModel, CadastroDto.class);
    }


    private void validarCadastro(CadastroModel cadastroModel) {
        if (cadastroModel == null) {
            throw new ParametroNullException("A Cadastro Model deve ser informada!");
        }

        if (cadastroModel.getCliente() == null) {
            throw new ParametroNullException("A Cliente Model deve ser informada!");
        }

        if (cadastroModel.getEndereco() == null) {
            throw new ParametroNullException("A Endereço Model deve ser informada!");
        }

        cadastroModel.getEndereco().setCliente(cadastroModel.getCliente());

        clienteService.validarCliente(cadastroModel.getCliente());

        enderecoService.validarEndereco(cadastroModel.getEndereco(), true);

        ClienteModel clienteModel = new ClienteModel(clienteService.insert(cadastroModel.getCliente()));

        cadastroModel.getEndereco().setCliente(new ClienteModel(clienteModel.getId()));
    }

}
