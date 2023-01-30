package com.ecommerce.tiagocustodio.model.dto;

import com.ecommerce.tiagocustodio.model.ClienteModel;
import com.ecommerce.tiagocustodio.model.UserModel;
import java.util.List;

public class ClienteDto {

    private Long id;
    private String cpf;
    private String nome;
    private List<CarrinhoDto> carrinhos;
    private List<EnderecoDto> enderecos;
    private List<PrecoDto> precos;
    private UserModel user;

    public ClienteDto() {}

    public ClienteDto(ClienteModel clienteModel) {
        this.id = clienteModel.getId();
        this.cpf = clienteModel.getCpf();
        this.nome = clienteModel.getNome();
        this.user = clienteModel.getUser();
    }

    public Long getId() {
        return id;
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

    public List<CarrinhoDto> getCarrinhos() {
        return carrinhos;
    }

    public void setCarrinhos(List<CarrinhoDto> carrinhos) {
        this.carrinhos = carrinhos;
    }

    public List<EnderecoDto> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoDto> enderecos) {
        this.enderecos = enderecos;
    }

    public List<PrecoDto> getPrecos() {
        return precos;
    }

    public void setPrecos(List<PrecoDto> precos) {
        this.precos = precos;
    }

    public UserModel getUser() { return user; }

}