package com.example.academiacx2.model;

import com.example.academiacx2.model.dto.EnderecoDto;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_endereco")
public class EnderecoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteModel clienteModel;

    public EnderecoModel() {
    }

    public EnderecoModel(EnderecoDto enderecoDto) {
        this.id = enderecoDto.getId();
        this.cep = enderecoDto.getCep();
        this.logradouro = enderecoDto.getLogradouro();
        this.numero = enderecoDto.getNumero();
        this.bairro = enderecoDto.getBairro();
        this.cidade = enderecoDto.getCidade();
        this.uf = enderecoDto.getUf();
        this.clienteModel = enderecoDto.getClienteModel();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLograouro() {
        return logradouro;
    }

    public void setLograouro(String lograouro) {
        this.logradouro = lograouro;
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

    public ClienteModel getClienteModel() {
        return clienteModel;
    }

    public void setClienteModel(ClienteModel clienteModel) {
        this.clienteModel = clienteModel;
    }
}
