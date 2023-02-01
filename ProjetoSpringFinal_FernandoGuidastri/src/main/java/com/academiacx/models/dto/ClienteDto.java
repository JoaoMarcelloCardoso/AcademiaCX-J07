package com.academiacx.models.dto;

import com.academiacx.models.ClienteModel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClienteDto {
    private String cpf;
    private String nome;
    private String email;

    @JsonProperty
            (access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClienteDto(String cpf, String nome, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }


    public ClienteDto() {
    }

    public ClienteDto(ClienteModel clienteModel) {
        this.cpf = clienteModel.getCpf();
        this.nome = clienteModel.getNome();
        this.email = clienteModel.getEmail();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
