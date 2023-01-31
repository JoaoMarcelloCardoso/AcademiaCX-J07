package com.example.academiacx2.model;

import com.example.academiacx2.model.dto.PrecoDto;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_preco")
public class PrecoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteModel clienteModel;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private ProdutoModel produtoModel;

    public PrecoModel() {
    }

    public PrecoModel(PrecoDto precoDto) {
        this.id = precoDto.getId();
        this.valor = precoDto.getValor();
        this.clienteModel = precoDto.getClienteModel();
        this.produtoModel = precoDto.getProdutoModel();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public ClienteModel getClienteModel() {
        return clienteModel;
    }

    public void setClienteModel(ClienteModel clienteModel) {
        this.clienteModel = clienteModel;
    }

    public ProdutoModel getProdutoModel() {
        return produtoModel;
    }

    public void setProdutoModel(ProdutoModel produtoModel) {
        this.produtoModel = produtoModel;
    }
}