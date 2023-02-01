package com.academiacx.models.dto;

import com.academiacx.models.ProdutoModel;

public class ProdutoDto {

    private String sku;
    private String nome;
    private Long id;
    public ProdutoDto() {
    }

    public ProdutoDto(String sku, String nome, Long id) {
        this.sku = sku;
        this.nome = nome;
        this.id = id;
    }
    public ProdutoDto(ProdutoModel produtoModel){
        this.id= produtoModel.getId();
        this.nome= produtoModel.getNome();
        this.sku= produtoModel.getSku();
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
}
