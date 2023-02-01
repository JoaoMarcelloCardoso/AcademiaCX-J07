package com.demo.academiacx.model.dto;

import com.demo.academiacx.model.ClienteModel;
import com.demo.academiacx.model.PrecoModel;
import com.demo.academiacx.model.ProdutoModel;

public class PrecoDto {

    private Long id;
    private double valor;
    private ProdutoModel produto;
    private ClienteModel cliente;

    public PrecoDto() {
    }

    public PrecoDto(PrecoModel precoModel){

        this.id = precoModel.getId();
        this.valor = precoModel.getValor();
        this.produto = precoModel.getProduto();
        this.cliente = precoModel.getCliente();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public ProdutoModel getProduto() {
        return produto;
    }

    public void setProduto(ProdutoModel produto) {
        this.produto = produto;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }
}
