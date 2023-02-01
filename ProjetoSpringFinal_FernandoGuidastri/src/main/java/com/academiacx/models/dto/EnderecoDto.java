package com.academiacx.models.dto;

import com.academiacx.models.EnderecoModel;

public class EnderecoDto {

    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnderecoDto() {
    }

    public EnderecoDto(EnderecoModel enderecoModel){
        this.logradouro=enderecoModel.getLogradouro();
        this.numero=enderecoModel.getNumero();
        this.bairro= enderecoModel.getBairro();
        this.cidade= enderecoModel.getCidade();
        this.uf= enderecoModel.getUf();
        this.id= enderecoModel.getId();
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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
}
