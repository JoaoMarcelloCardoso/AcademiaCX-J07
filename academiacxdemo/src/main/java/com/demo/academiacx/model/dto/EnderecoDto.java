package com.demo.academiacx.model.dto;

import com.demo.academiacx.model.ClienteModel;
import com.demo.academiacx.model.EnderecoModel;
import jakarta.persistence.Column;

public class EnderecoDto {

    private Long id;
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private ClienteModel cliente;

    public EnderecoDto() {
    }

    public EnderecoDto(EnderecoModel enderecoModel) {
        this.id = enderecoModel.getId();
        this.cep = enderecoModel.getCep();
        this.cidade = enderecoModel.getCidade();
        this.uf = enderecoModel.getUf();
        this.bairro = enderecoModel.getBairro();
        this.logradouro = enderecoModel.getLogradouro();
        this.cliente = enderecoModel.getCliente();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }
}
