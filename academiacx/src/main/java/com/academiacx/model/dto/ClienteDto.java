package com.academiacx.model.dto;

import com.academiacx.model.ClienteModel;

import java.time.LocalDate;

public class ClienteDto {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private LocalDate dataNasc;


    public ClienteDto() {

    }

    public ClienteDto(ClienteModel clienteModel) {
        this.id = clienteModel.getId();
        this.nome = clienteModel.getNome();
        this.cpf = clienteModel.getCpf();
        this.email = clienteModel.getEmail();
        this.telefone = clienteModel.getTelefone();
        this.dataNasc = clienteModel.getDataNasc();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }
}
