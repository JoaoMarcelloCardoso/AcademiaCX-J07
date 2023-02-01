package com.demo.academiacx.model.dto.user;

import com.demo.academiacx.model.ClienteModel;
import com.demo.academiacx.model.CompraModel;

import java.util.List;

public class ClienteDto {

    private Long id;
    private String nome;
    private String cpf;

    private List<CompraModel> compras;
    public ClienteDto() {

    }

    public ClienteDto(ClienteModel clienteModel) {
        this.id = clienteModel.getId();
        this.nome = clienteModel.getNome();
        this.cpf = clienteModel.getCpf();
        this.compras = clienteModel.getCompras();
    }

    public ClienteDto(UserDto userDto) {
        this.nome = userDto.getNome();
        this.cpf = userDto.getCpf();
    }
    public List<CompraModel> getCompras() {
        return compras;
    }

    public void setCompras(List<CompraModel> compras) {
        this.compras = compras;
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

}
