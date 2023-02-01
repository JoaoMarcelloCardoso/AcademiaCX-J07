package com.demo.academiacx.model.dto;

import com.demo.academiacx.model.ClienteModel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClienteDto {


    private Long id;
    private String cpf;
    private String nome;
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public ClienteDto() {
    }

    public ClienteDto(ClienteModel clienteModel){
        this.id = clienteModel.getId();
        this.cpf = clienteModel.getCpf();
        this.nome = clienteModel.getNome();
        this.username = clienteModel.getUsername();
        this.password = clienteModel.getPassword();
    }

    public Long getId() {
        return id;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPasswordDecrypt(){
        return password;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
