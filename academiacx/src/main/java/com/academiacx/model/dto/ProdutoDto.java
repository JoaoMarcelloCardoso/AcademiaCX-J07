package com.academiacx.model.dto;

import com.academiacx.model.ProdutoModel;

import java.math.BigDecimal;

public class ProdutoDto {

    private Long id;
    private String sku;
    private String nome;
    private BigDecimal preco;


    public ProdutoDto() {

    }

    public ProdutoDto(ProdutoModel produtoModel) {
        this.id = produtoModel.getId();
        this.nome = produtoModel.getNome();
        this.sku = produtoModel.getSku();
        this.preco = produtoModel.getPreco();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
