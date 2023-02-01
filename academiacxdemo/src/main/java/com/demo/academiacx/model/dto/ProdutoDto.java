package com.demo.academiacx.model.dto;

import com.demo.academiacx.model.ProdutoModel;
import jakarta.persistence.Column;

public class ProdutoDto {

    private Long id;

    private String sku;
    private String nome;

    public ProdutoDto() {
    }

    public ProdutoDto(ProdutoModel produtoModel){

        this.id = produtoModel.getId();
        this.sku = produtoModel.getSku();
        this.nome = produtoModel.getNome();

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
