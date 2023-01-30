package com.ecommerce.tiagocustodio.model.dto;

import com.ecommerce.tiagocustodio.model.EnderecoModel;

public class EnderecoDto {
    private Long id;
    private String rua;
    private String cidade;
    private String estado;

    private ClienteDto clienteModel;

    public EnderecoDto() {
    }

    public EnderecoDto(EnderecoModel enderecoModel) {
        this.id = enderecoModel.getId();
        this.rua = enderecoModel.getLogradouro();
        this.cidade = enderecoModel.getCidade();
        this.estado = enderecoModel.getUf();
        this.clienteModel = new ClienteDto(enderecoModel.getClienteModel());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ClienteDto getClienteModel() {
        return clienteModel;
    }

    public void setClienteModel(ClienteDto clienteModel) {
        this.clienteModel = clienteModel;
    }
}