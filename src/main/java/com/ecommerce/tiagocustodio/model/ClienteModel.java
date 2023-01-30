package com.ecommerce.tiagocustodio.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cliente")
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String nome;

    @OneToMany(mappedBy = "clienteModel")
    private List<CarrinhoModel> carrinhos;

    @OneToMany(mappedBy = "clienteModel")
    private List<EnderecoModel> enderecos;

    @OneToMany(mappedBy = "clienteModel")
    private List<PrecoModel> precos;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    public ClienteModel() {
    }

    public ClienteModel(Long id, String cpf, String nome, List<CarrinhoModel> carrinhos, List<EnderecoModel> enderecos, List<PrecoModel> precos, UserModel user) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.carrinhos = carrinhos;
        this.enderecos = enderecos;
        this.precos = precos;
        this.user = user;
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

    public List<CarrinhoModel> getCarrinhos() {
        return carrinhos;
    }

    public void setCarrinhos(List<CarrinhoModel> carrinhos) {
        this.carrinhos = carrinhos;
    }

    public List<EnderecoModel> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoModel> enderecos) {
        this.enderecos = enderecos;
    }

    public List<PrecoModel> getPrecos() {
        return precos;
    }

    public void setPrecos(List<PrecoModel> precos) {
        this.precos = precos;
    }

    public UserModel getUser() {
        return user;
    }
}