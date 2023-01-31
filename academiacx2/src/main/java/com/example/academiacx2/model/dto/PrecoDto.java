package com.example.academiacx2.model.dto;

import com.example.academiacx2.model.ClienteModel;
import com.example.academiacx2.model.PrecoModel;
import com.example.academiacx2.model.ProdutoModel;

public class PrecoDto {

    private Long id;
    private Double valor;
    private ClienteModel clienteModel;
    private ProdutoModel produtoModel;

    public PrecoDto() {
    }

    public PrecoDto(PrecoModel precoModel) {
        this.id = precoModel.getId();
        this.valor = precoModel.getValor();
        this.clienteModel = precoModel.getClienteModel();
        this.produtoModel = precoModel.getProdutoModel();
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
