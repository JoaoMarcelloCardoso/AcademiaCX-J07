package com.academiacx.model;

import com.academiacx.model.dto.CadastroDto;


public class CadastroModel {

    private ClienteModel cliente;
    private EnderecoModel endereco;

    public CadastroModel() {

    }

    public CadastroModel(CadastroDto cadastroDto) {
        this.cliente = new ClienteModel(cadastroDto.getCliente());
        this.endereco = new EnderecoModel(cadastroDto.getEndereco());
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public EnderecoModel getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }
}
