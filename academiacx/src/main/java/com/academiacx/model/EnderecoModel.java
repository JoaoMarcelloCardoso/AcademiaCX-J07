package com.academiacx.model;

import com.academiacx.model.dto.EnderecoDto;
import jakarta.persistence.*;


@Entity
@Table(name = "tb_endereco")
public class EnderecoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    @Column(nullable = false)
    private String cidade;
    @Column(nullable = false)
    private String uf;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteModel cliente;


    public EnderecoModel() {

    }

    public EnderecoModel(Long id) {
        this.id = id;
    }

    public EnderecoModel(EnderecoDto enderecoDto) {
        this.id = enderecoDto.getId();
        this.cep = enderecoDto.getCep();
        this.cidade = enderecoDto.getCidade();
        this.uf = enderecoDto.getUf();
        this.bairro = enderecoDto.getBairro();
        this.logradouro = enderecoDto.getLogradouro();
        this.numero = enderecoDto.getNumero();
        this.complemento = enderecoDto.getComplemento();
        this.cliente = new ClienteModel(enderecoDto.getCliente());
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
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
