package com.academiacx.model.dto;


import com.academiacx.model.CadastroModel;

import java.time.LocalDate;

public class CadastroDto {

    private ClienteDto cliente;
    private EnderecoDto endereco;

    public CadastroDto() {

    }

    public CadastroDto(CadastroModel cadastroModel) {
        this.cliente = new ClienteDto(cadastroModel.getCliente());
        this.endereco = new EnderecoDto(cadastroModel.getEndereco());
    }

    public ClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
    }

    public EnderecoDto getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDto endereco) {
        this.endereco = endereco;
    }
}
